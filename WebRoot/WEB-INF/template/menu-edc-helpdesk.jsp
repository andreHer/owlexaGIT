<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<tr>
    <td colspan="2" class="subMenuBar2" width="100%">
    
    	<div id="header">
			<div id="menu-container">
			    <div id="openCloseIdentifier"></div>
							
    	

		        <div id="cssmenu">
		            <ul >
		            	<li >				 
							<img src="images/owlexa/owlexa_letter_logo.png" alt="Owlexa Healthcare"  border="0" height="50" width="53">				
						</li>
		                <li class="has-sub" >
		                    <a href="#">Claim</a>
		                    <ul>
		                        <li>
		                            <a href="member-rki-form">Register EDC</a>
		                        </li>
		                        <li>
		                            <a href="claim">Search Claim</a>
		                        </li>
		                    </ul>
		                </li>
		           
		                <li class="has-sub" >
		                    <a href="#">Membership</a>
		                    <ul>
		                        <li>
		                            <a href="member">Search Member</a>
		                        </li>
		                    </ul>
		                </li>                
		                <li  class="has-sub" >
		                    <a href="#">Provider Relation</a>
		                    <ul>
		                    	<li>
		                            <a href="provider-form">Register Provider</a>
		                        </li>                       
		                        <li>
		                            <a href="provider">Search Provider</a>
		                        </li>
		                    </ul>
		                </li>                
		              
		                <li>
		                    <a href="edctransactionlog">EDC Monitoring</a>                    
		                </li>                
		                <li>
		                    <a href="user?navigation=logout">Logout</a>
		                </li>
		
						<li>
		                	<img src="images/owlexa/Lintasarta.png" alt="Aplikanusa Lintasarta"  border="0"    height="40" width="97">
		                </li>
		
		            </ul>
		        </div>
		        
			<!-- End Menu Container -->
			</div>
			<div id="handle" class="handle-container">
			    <div id="openCloseButton" class="handle-button-item">
			    	<span><img src="images/owlexa/arrow_up.png"/>Menu</span>
			    </div>
			</div>
		</div>
		        
    </td>
</tr>