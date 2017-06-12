<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>     
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery-3.2.1.min.js"></script>
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/material.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ripples.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.bundle.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
<title>Estadisticas</title>
</head>
<t:paginabasicaAdmin title="Estadisticas">
<body>

<div class="accordion" id="accordion" role="tablist" aria-multiselectable="true">
			<div id="colaboracionMes"class="card">
				<div class="card-header" role="tab" id="headingOne">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne">
						<h5 class="mb-0">
							Colaboraciones por mes del año actual <i
								class="fa fa-angle-down rotate-icon"></i>
						</h5>
					</a>
				</div>
				<div id="collapseOne" class="collapse" role="tabpanel"
					aria-labelledby="headingOne">
					<div class="card-block">
						<canvas id="myChartColaboraciones" width="200" height="60"></canvas>
							<select id="estadisticasMes" style="visibility: hidden">
								<c:forEach items="${estadisticasMes}" var="elem">
									<option>${elem.value}</option>
								</c:forEach>
							</select>
						</form>
						<br>
						<button style="left: 44%; position: relative;" id="download" class="btn btn-raised btn-primary">Generar PDF</button>
						<script>
							var chartColaboraciones = document.getElementById("myChartColaboraciones");
							var v = document.getElementById("estadisticasMes");
							var vector = [];
							for (var pos = 0; pos < v.options.length; pos++) {
								vector.push(v.options.item(pos).value);
							}
							var myChart = new Chart(chartColaboraciones, {
								type : 'bar',
								data : {
									labels : [ "Enero", "Febrero", "Marzo",
											"Abril", "Mayo", "Junio", "Julio",
											"Agosto", "Septiembre", "Octubre",
											"Noviembre", "Diciembre" ],
									datasets : [ {
										label : 'Colaboraciones',
										data : vector,
										backgroundColor : [
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)',
												'rgba(54, 162, 235, 0.2)'

										],
										borderColor : [
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)',
												'rgba(54, 162, 235, 1)'

										],
										borderWidth : 1
									} ]
								},
								options : {
									scales : {
										yAxes : [ {
											ticks : {
												beginAtZero : true
											}
										} ]
									}
								}
							});
							var download=document.getElementById("download");
							download.addEventListener("click", function() {
								var pdf = new jsPDF('p','pt','a4');
								pdf.addHTML(document.getElementById("colaboracionMes"),function() {	
									
								    pdf.save('estadisticasPorMes.pdf');
								});
							}, false);
						</script>
					</div>
				</div>
			</div>
			<div id="colaboracionesHabilidad"class="card">
        <div  class="card-header" role="tab" id="headingTwo">
            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                <h5 class="mb-0">
                Habilidades <i class="fa fa-angle-down rotate-icon"></i>
            </h5>
            </a>
        </div>
				<div id="collapseTwo" class="collapse" role="tabpanel"
					aria-labelledby="headingTwo">
					<div class="card-block">
					<canvas id="myChartHabilidades" width="200" height="60"></canvas>
						<form>
							<select id="nombresHabilidades" style="visibility: hidden">
								<c:forEach items="${estadisticasHabilidades}" var="elem">
									<option>${elem.key}</option>
								</c:forEach>
							</select>
							<select id="estadisticasHabilidades" style="visibility: hidden">
								<c:forEach items="${estadisticasHabilidades}" var="elem">
									<option>${elem.value}</option>
								</c:forEach>
							</select>
						</form>
						<button style="left: 44%; position: relative;" id="download2" class="btn btn-raised btn-primary">Generar PDF</button>
						<script>
					var chartHabilidades = document.getElementById("myChartHabilidades").getContext('2d');
					var valores = document.getElementById("estadisticasHabilidades");
					var vectorValores = [];
					for (var pos = 0; pos < valores.options.length; pos++) {
						vectorValores.push(valores.options.item(pos).value);
					}
					var nombres = document.getElementById("nombresHabilidades");
					var vectorNombres = [];
					for (var pos = 0; pos < nombres.options.length; pos++) {
						vectorNombres.push(nombres.options.item(pos).value);
					}
					var myChart = new Chart(chartHabilidades, {
					    type: 'pie',
					    data: {
					        labels: vectorNombres,
					        datasets: [{
					            label: '# of Votes',
					            data: vectorValores,
					            backgroundColor: [
					                'rgba(255, 99, 132, 0.2)',
					                'rgba(54, 162, 235, 0.2)',
					                'rgba(255, 206, 86, 0.2)',
					                'rgba(255, 152, 0, 0.7)',
					                'rgba(0, 188, 212, 0.7)',
					                'rgba(0, 150, 136, 0.7)',
					                'rgba(121, 85, 72, 0.7)',
					                'rgba(205, 220, 57, 0.3)'
					            ],
					            borderColor: [
					                'rgba(255,99,132,1)',
					                'rgba(54, 162, 235, 1)',
					                'rgba(255, 206, 86, 1)',
					                'rgba(255, 152, 0, 1)',
					                'rgba(0, 188, 212, 1)',
					                'rgba(0, 150, 136, 1)',
					                'rgba(121, 85, 72, 1)',
					                'rgba(205, 220, 57, 1)'
					            ],
					            borderWidth: 1
					        }]
					    }
					});
					var download2=document.getElementById("download2");
					download2.addEventListener("click", function() {
						var pdf = new jsPDF('p','pt','a4');
						pdf.addHTML(document.getElementById("colaboracionesHabilidad"),function() {	
							
						    pdf.save('estadisticasPorHabilidades.pdf');
						});
					}, false);
					</script>					
					</div>
				</div>
			</div>
		</div>
</body>
</t:paginabasicaAdmin>
</html>