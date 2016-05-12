/**
 * Edited by Resshin
 */

(function($) {
    $.extend({
        tablesorterPager: new function() {

            function updatePageDisplay(c) {
                $(c.cssPageDisplay,c.container).val((c.page+1) + c.separator + c.totalPages);

                if(c.page <= 0) {
                    $(c.cssFirst,c.container).addClass("disabled");
                    $(c.cssPrev,c.container).addClass("disabled");
                } else {
                    $(c.cssFirst,c.container).removeClass("disabled");
                    $(c.cssPrev,c.container).removeClass("disabled");
                }

                if(c.page >= (c.totalPages-1)) {
                    $(c.cssNext,c.container).addClass("disabled");
                    $(c.cssLast,c.container).addClass("disabled");
                } else {
                    $(c.cssNext,c.container).removeClass("disabled");
                    $(c.cssLast,c.container).removeClass("disabled");
                }

                //$(c.cssGoToPage,c.container).children("[value!='" + c.page + "']").attr("selected", "");
                //$(c.cssGoToPage,c.container).children("[value='" + c.page + "']").attr("selected", "selected");
                $(c.cssGoToPage,c.container).val(c.page+1);
                $(c.cssResultSize,c.container).html(c.totalRows);
                $(c.cssPageSize,c.container).html(c.totalPages);
            }

            function setPageSize(table,size) {

                var c = table.config;
                c.size = size;
                if(c.size == 0) {
                    c.totalPages = 0;
                } else {
                    c.totalPages = Math.ceil(c.totalRows / c.size);
                }
                c.pagerPositionSet = false;

                moveToPage(table);
                fixPosition(table);
            }

            function fixPosition(table) {
                var c = table.config;
                if(!c.pagerPositionSet && c.positionFixed) {
                    var c = table.config, o = $(table);
                    if(o.offset) {
                        c.container.css({
                            top: o.offset().top + o.height() + 'px',
                            position: 'absolute'
                        });
                    }
                    c.pagerPositionSet = true;
                }
            }

            function moveToFirstPage(table) {
                var c = table.config;
                c.page = 0;
                moveToPage(table);
            }

            function moveToLastPage(table) {
                var c = table.config;
                c.page = (c.totalPages-1);
                moveToPage(table);
            }

            function moveToNextPage(table) {
                var c = table.config;
                c.page++;
                if(c.page > (c.totalPages-1)) {
                    c.page = (c.totalPages-1);
                }
                moveToPage(table);
            }

            function moveToPrevPage(table) {
                var c = table.config;
                c.page--;
                if(c.page < 0) {
                    c.page = 0;
                }
                moveToPage(table);
            }

            function moveToSpecificPage(table, page) {
                var c = table.config;
                c.page = page;
                moveToPage(table);
            }


            function moveToPage(table) {
                var c = table.config;

                renderTable(table,c.rowsCopy);
            }

            function renderTable(table,rows) {

                var c = table.config;
                var s = (c.page * c.size);
                var e = (s + c.size);
                if(e > rows.length ) {
                    e = rows.length;
                }


                var tableBody = $(table.tBodies[0]);

                // clear the table body

                $.tablesorter.clearTableBody(table);

                for(var i = s; i < e; i++) {

                    //tableBody.append(rows[i]);

                    var o = rows[i];
                    var l = o.length;
                    for(var j=0; j < l; j++) {

                        tableBody[0].appendChild(o[j]);
                    }
                }

                fixPosition(table,tableBody);

                $(table).trigger("applyWidgets");

                if( c.page >= c.totalPages ) {
                    moveToLastPage(table);
                }

                updatePageDisplay(c);
            }

            this.appender = function(table,rows) {
                var c = table.config;

                c.rowsCopy = rows;
                c.totalRows = rows.length;
                c.totalPages = Math.ceil(c.totalRows / c.size);

                renderTable(table,rows);
            };

            this.defaults = {
                size: 10,
                offset: 0,
                page: 0,
                totalRows: 0,
                totalPages: 0,
                container: null,
                cssNext: '.next',
                cssPrev: '.prev',
                cssFirst: '.first',
                cssLast: '.last',
                cssPageDisplay: '.pagedisplay',
                cssPageSize: '.pagesize',
                cssGoToPage: '.gotopage',
                cssResultSize: '.resultsize',
                separator: "/",
                positionFixed: true,
                appender: this.appender
            };

            this.construct = function(settings) {

                return this.each(function() {

                    config = $.extend(this.config, $.tablesorterPager.defaults, settings);

                    var table = this, pager = config.container;

                    $(this).trigger("appendCache");

                    $(config.cssFirst,pager).unbind("click");
                    $(config.cssFirst,pager).click(function() {
                        moveToFirstPage(table);
                        return false;
                    });
                    $(config.cssPrev,pager).unbind("click");
                    $(config.cssPrev,pager).click(function() {
                        moveToPrevPage(table);
                        return false;
                    });
                    $(config.cssNext,pager).unbind("click");
                    $(config.cssNext,pager).click(function() {
                        moveToNextPage(table);
                        return false;
                    });
                    $(config.cssLast,pager).unbind("click");
                    $(config.cssLast,pager).click(function() {
                        moveToLastPage(table);
                        return false;
                    });
                    $(config.cssGoToPage,pager).unbind("keypress");
                    $(config.cssGoToPage,pager).keypress(function(e){
                        if(e.keyCode==13) {
                            moveToSpecificPage(table,parseInt($(this).val()-1));
                            return false;
                        }
                    })
                    $(config.cssPageSize,pager).unbind("change");
                    $(config.cssPageSize,pager).change(function() {
                        setPageSize(table,parseInt($(this).val()));
                        return false;
                    });
                    setPageSize(table, config.size);
                });
            };

        }
    });
    // extend plugin scope
    $.fn.extend({
        tablesorterPager: $.tablesorterPager.construct
    });

})(jQuery);				