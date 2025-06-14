// Script para mantener los filtros al cambiar de página
document.addEventListener('DOMContentLoaded', function() {
	const filterForm = document.getElementById('filterForm');
	const pageLinks = document.querySelectorAll('.page-link');

	pageLinks.forEach(link => {
		link.addEventListener('click', function(e) {
			if (this.getAttribute('href') && !this.closest('.disabled')) {
				const url = new URL(this.href);
				filterForm.elements.page.value = url.searchParams.get('page');
				filterForm.submit();
				e.preventDefault();
			}
		});
	});
});