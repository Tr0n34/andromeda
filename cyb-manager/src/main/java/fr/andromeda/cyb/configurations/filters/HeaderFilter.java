package fr.andromeda.cyb.configurations.filters;

import fr.andromeda.api.enums.Header;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DurationFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;

@Component
public class HeaderFilter extends OncePerRequestFilter {

    private static final String PATTERN_SIGNATURE = "%s::%s::%s::%s";
    private static final String ALGORITHM_HMAC = "HmacSHA256";

    @Value("${hmac.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        if ( doFilterException(request.getRequestURI(), request, response, filterChain) ) {
            return;
        }
        filterChain.doFilter(request, responseWrapper);
        long latency = System.currentTimeMillis() - start;
        responseWrapper.setHeader(Header.LATENCY.getName(), String.valueOf(latency) + DurationFormat.Unit.MILLIS.asSuffix());
        responseWrapper.setHeader(Header.SIGNATURE.getName(), generateHmacSignature(sign(request)));
        responseWrapper.copyBodyToResponse();
    }

    private boolean doFilterException(String path, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isFilterException = false;
        if ( path.startsWith("/swagger") || path.startsWith("/v3/api-docs") ) {
            filterChain.doFilter(request, response);
            isFilterException = true;
        }
        return isFilterException;
    }

    private String sign(HttpServletRequest request) {
        String requestId = request.getHeader(Header.REQUEST_ID.getName());
        if (requestId == null || requestId.isBlank()) {
            requestId = java.util.UUID.randomUUID().toString();
        }
        String headersString = java.util.Collections.list(request.getHeaderNames())
                .stream()
                .map(name -> name + "=" + request.getHeader(name))
                .collect(Collectors.joining(";"));
        return PATTERN_SIGNATURE.formatted(
                requestId,
                request.getMethod(),
                request.getRequestURI(),
                headersString
        );
    }

    private String generateHmacSignature(String data) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM_HMAC);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM_HMAC);
            mac.init(secretKeySpec);
            byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération de la signature", e);
        }
    }

}
