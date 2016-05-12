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
		                <li >
		                    <a href="dashboard?navigation=pusat">Dashboard</a>
		                </li>
		
		              
		                <li class="has-sub" >
		                    <a href="#">Membership</a>
		                    <ul>
		                     
		                        <li>
		                            <a href="membergroup">Search Group</a>
		                        </li>
		                        <li>
		                            <a href="member">Search Member</a>
		                        </li>                       
		                        <li>
		                            <a href="client">Search Client</a>
		                        </li>
		
		                    </ul>
		                </li>
		                
		               
		                <li  class="has-sub" >
		                    <a href="#">Excess Management</a>
		                    <ul>
		                        <li>
		                            <a href="invoice-form?navigation=tambah">Register Invoice</a>
		                        </li>
		                        <li>
		                            <a href="excesscharge">Excess Charge</a>
		                        </li>
		                        <li>
		                            <a href="invoice">Search Invoice</a>
		                        </li>
		                    </ul>
		                </li>
		                             
		                <li class="has-sub" >
		                    <a href="#">Report</a>
		                    <ul>                
		                        <li>
		                            <a href="reportrequest">Request Report</a>
		                        </li>
		
		                    </ul>
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