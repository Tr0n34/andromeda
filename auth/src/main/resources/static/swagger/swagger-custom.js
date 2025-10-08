const observer = new MutationObserver(() => {
    document.querySelectorAll('button.try-out').forEach(btn => btn.remove());
});

// Observer le body pour les ajouts dynamiques
observer.observe(document.body, { childList: true, subtree: true });