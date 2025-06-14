window.onload = function() {
	const params = new URLSearchParams(window.location.search);
	if (params.get("error")) {
		const mensajeError = document.getElementById("mensaje");
		mensajeError.textContent = "Credenciales incorrectas. Intenta nuevamente.";
		mensajeError.classList.remove("d-none");
	}
};