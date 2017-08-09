function switchLang(node) {
    var currentPath = encodeURIComponent(window.location.href.replace(window.location.protocol + "//" + window.location.host, ""));
    window.location.href="/locale?lang=" + node.value + "&redirectTo=" + currentPath;
}