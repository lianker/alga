$('#confimacaoExclusaoModal').on('show.bs.modal', function (event) {
	// Button that triggered the modal
	var button = $(event.relatedTarget); 
	// Extract info from data-* attributes
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao'); 

	var modal = $(this); 
	var form = modal.find('form'); 
	var action = form.data('url-base'); 
	
	if (!action.endsWith('/')) { 
		action += '/'; 
	} 
	form.attr('action', action + codigoTitulo); 
	 	 
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?'); 

})