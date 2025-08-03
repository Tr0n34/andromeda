package fr.andromeda.sport.configurations;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.scalars.datetime.DateTimeScalar;
import graphql.schema.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Configuration
public class GraphQLConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLConfiguration.class);

    public static final String DATETIME_SCALAR_NAME = "DateTime";

    @Bean
    GraphQlSourceBuilderCustomizer inspectionCustomizer() {
        return source -> source.inspectSchemaMappings(report -> logger.info(report.toString()));
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(GraphQLScalarType dateTimeScalar) {
        return wiringBuilder -> wiringBuilder.scalar(dateTimeScalar);
    }

    @Bean
    public GraphQLScalarType dateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name(DATETIME_SCALAR_NAME)
                .description("A custom scalar that handles ISO-8601 date-time format")
                .coercing(new Coercing<LocalDateTime, String>() {

                    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

                    @Override
                    public @Nullable String serialize(@NonNull Object dataFetcherResult,
                                                      @NonNull GraphQLContext graphQLContext,
                                                      @NonNull Locale locale) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof LocalDateTime) {
                            return formatter.format((LocalDateTime) dataFetcherResult);
                        }
                        throw new CoercingSerializeException("Expected a LocalDateTime object.");
                    }

                    @Override
                    public @Nullable LocalDateTime parseValue(@NonNull Object input,
                                                              @NonNull GraphQLContext graphQLContext,
                                                              @NonNull Locale locale) throws CoercingParseValueException {
                        try {
                            return LocalDateTime.parse(input.toString(), formatter);
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException("Invalid ISO date-time format.");
                        }
                    }

                    @Override
                    public @Nullable LocalDateTime parseLiteral(@NonNull Value<?> input,
                                                                @NonNull CoercedVariables variables,
                                                                @NonNull GraphQLContext graphQLContext,
                                                                @NonNull Locale locale) throws CoercingParseLiteralException {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDateTime.parse(((StringValue) input).getValue(), formatter);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException("Invalid ISO date-time literal.");
                            }
                        }
                        throw new CoercingParseLiteralException("Expected AST type 'StringValue'.");
                    }

                    @Override
                    public @NonNull Value<?> valueToLiteral(@NonNull Object input,
                                                            @NonNull GraphQLContext graphQLContext,
                                                            @NonNull Locale locale) {
                        if (input instanceof LocalDateTime) {
                            return new StringValue(formatter.format((LocalDateTime) input));
                        }
                        throw new CoercingSerializeException("Expected LocalDateTime to convert to literal.");
                    }
                })
                .build();
    }

}
