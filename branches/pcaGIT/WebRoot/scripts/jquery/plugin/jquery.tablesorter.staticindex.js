$.tablesorter.addWidget({
    // give the widget a id
    id: "staticIndex",
    // format is called when the on init and when a sorting has finished
    format: function(table) {
        $(table).find("tbody td.index").each(function(i){
            if(table.config.page != undefined && table.config.size != undefined) {
                //if using pager plugin
                $(this).html(table.config.page * table.config.size + i + 1);
            } else {
                $(this).html(i + 1);
            }
        });
    }
});