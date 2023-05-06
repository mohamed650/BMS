$(document).ready(function(){
    loadTransactionsHistory();
});

var table;
function loadTransactionsHistory(){
    table = new Tabulator("#transactionHistoryTabulator", {
        layout:"fitData",
		height: "360px",
        pagination: "local",
    	paginationSize: 10,
    	paginationSizeSelector: [3, 6, 8, 10],
    	movableColumns: true,
    	paginationCounter:"rows",
		ajaxURL    :  "/loadTransactionsHistory",
		ajaxConfig : "get",
		width: 50,
		headerSort: false,
      	cssClass: 'text-center',
      	frozen: true,
        columns    : [
        {title:"Transaction Id", field:"transaction_Id"},
        {title:"Date", field:"transaction_Date" ,hozAlign:"center"},
        {title:"Account Number", field:"to_account_Number"},
        {title:"Amount", field:"amount"},
        {title:"Cr / Dr", field:"cr_dr", formatter:function(cell){
            var value = cell.getValue();
            var color = value === "cr" ? "green" : "red";
            return "<div style='color: "+color+";'>"+value+"</div>";
        }},
        {title:"Description", field:"description"},
        ],
    });
}