const observer = new MutationObserver(() => {
    document.querySelectorAll('button.try-out').forEach(btn => btn.remove());
});
observer.observe(document.body, { childList: true, subtree: true });