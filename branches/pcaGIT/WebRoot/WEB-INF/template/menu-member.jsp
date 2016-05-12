<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<tr>
    <td colspan="2" class="subMenuBar2" nowrap="nowrap" width="100%">
    	<div id="header">
			<div id="menu-container">
			    <div id="openCloseIdentifier"></div>
							
		        <div id="cssmenu">
		            <ul >
		            	<li >				 
							<img src="images/owlexa/owlexa_letter_logo.png" alt="Owlexa Healthcare"  border="0" height="50" width="53">				
						</li>
		            
		                <li>
		                    <a href="claim">Track My Claim</a>                 
		                </li>
		                <li>
		                    <a href="member?navigation=listdependant">My Dependent</a>                 
		                </li>
		                <li >
		                    <a href="excesscharge">My Excess Charges</a>                   
		                </li>                
		                <li >
		                    <!-- 
		                    <a href="memberprovider?navigation=myprovider">Available Provider</a>
		                    -->    
		                    <c:choose>
							    <c:when test="${empty theMember}">
							       <a href="memberprovider?navigation=myprovider">Available Provider</a>
							    </c:when>
							    <c:otherwise>
							        <a href="provider?navigation=listmember&memberId=<c:out value="${theMember.memberId}" />">Available Provider</a>
							    </c:otherwise>
							</c:choose>                  
		                </li>
		                <li >
		                    <a href="map?navigation=myprovider">Peta Provider</a>                  
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