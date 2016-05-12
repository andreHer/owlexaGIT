/*created by aju on 20150430, apter gugling2 :P*/

function toggleMenu(isOpen){
	if(isOpen==true){
		$("#menu-container").animate({
            marginTop: "0px"
            }, 500 );
		$("#openCloseButton").html("<span><img src=\"images/owlexa/arrow_up.png\"/>Menu</span>");
        $("#openCloseIdentifier").show();
        $("#welcomeBar").show();
        $("#welcomeBar").css("height","50px");
	}
	else{
		$("#menu-container").animate({
            marginTop: "-50px"
            }, 500 );
		$("#openCloseButton").html("<span><img src=\"images/owlexa/arrow_down.png\"/>Menu</span>");
        $("#openCloseIdentifier").hide();
        $("#welcomeBar").hide();
	}
}

function toggleSidebar(isOpen){
	if(isOpen==true){
		$("#sidebar-container").animate({
        	opacity: "show"
            }, 500 );
        $("#openCloseButton-sidebar").html("<span><img src=\"images/owlexa/arrow_up.png\"/>Sidebar</span>");
        $("#openCloseIdentifier-sidebar").show();
	}
	else{
		$("#sidebar-container").animate({
            opacity: "hide"
            }, 500 );
        $("#openCloseButton-sidebar").html("<span><img src=\"images/owlexa/arrow_down.png\"/>Sidebar</span>");
        $("#openCloseIdentifier-sidebar").hide();
	}
}

$(document).ready(function(){
	
	/*Check menu state*/
	if ($.cookie("menuState") === "opened") {
		toggleMenu(true);
	}
	else{
		toggleMenu(false);
	}
	
	/*menu show hide*/
    $("#handle").click(function(){
        if ($("#openCloseIdentifier").is(":visible")) {
        	toggleMenu(false);
            $.cookie("menuState","closed", { expires: 1 });
        } else {
        	toggleMenu(true);
            $.cookie("menuState","opened", { expires: 1 });
        }
    });
    
    /*Check sidebar state*/
	if ($.cookie("sidebarState") === "opened") {
		toggleSidebar(true);
	}
	else{
		toggleSidebar(false);
	}
    
    /*sidebar show hide*/
    $("#handle-sidebar").click(function(){
        if ($("#openCloseIdentifier-sidebar").is(":visible")) {
            toggleSidebar(false);
            $.cookie("sidebarState","closed", { expires: 1 });
        } else {
        	toggleSidebar(true);
            $.cookie("sidebarState","opened", { expires: 1 });
        }
    });

});