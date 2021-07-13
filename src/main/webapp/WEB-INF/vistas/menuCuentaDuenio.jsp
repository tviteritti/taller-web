	<div class="card text-center">
	<div class="row card-header">
	    	<div class="col-3"> <a href="loginVeterinaria"><i class="fas fa-home display-6 text-body"></i></a></div>
	    	<div class="col-2 d-flex flex-row">  <i class="fas fa-bell display-6 mr-3"></i>
	    	
		    				<div class="dropdown" style="line-height:16px">
								  <a class="btn btn-secondary dropdown-toggle p-0" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="height:20px;width:50px">
								   <p style="font-size:14px"> ${cantidadNotificaciones}</p>
								  </a>
								
								  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								  
								  		<c:forEach items="${notificacion}" var="n">
								    	<c:if test="${not empty n.usuario.id }">
								    	  <c:if test="${n.usuario.id eq usuario.id}">
								  
									    <a class="dropdown-item" href="notificaciones?id=${n.id}"> ${n.mensaje} </a>
									    <script>
								          document.getElementsByClassName("fas fa-bell")[0].classList.add('text-danger');
								        </script>
									    </c:if>	
								    	</c:if>
							    	</c:forEach>
								    
								  </div>
							</div>
							

	    	</div>
	    	<div class="col-2"><a href="cuentaDuenio"><i class="fas fa-user text-body" style="cursor:pointer"></i></a></div>
	    	<div class="col-2"><i class="fas fa-moon"></i></div>
	    	<div class="col-3">
		    	<a href="cerrarSesion" class="text-decoration-none text-body">cerrar sesion
		    		<i class="fas fa-sign-out-alt display-5 text-body"></i>
		    	</a>
	    	</div>
	    	<div class="border-bottom"></div>
	    </div>
	    <nav class="navbar navbar-dark bg-dark">
	
		<ul class="navbar-nav mr-auto d-flex flex-row">
	      <li class="nav-item d-inline mr-5">
	       
	        <form action="mascota" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Mi mascota</button>
	        </form>
	      </li>
	      <li class="nav-item d-inline mr-5">
	        
	        <form action="turnos" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Turnos</button>
	        </form>
	      </li>
	      <br>
	      <li class="nav-item d-inline mr-5">
	      <form action="planes" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Planes</button>
	        </form>
	       
	      </li>
	      <br>
	      <li class="nav-item d-inline mr-5">
	        <form action="consultas" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Consultas</button>
	        </form>
	      </li>
       </ul>
	   
	</nav>
	 