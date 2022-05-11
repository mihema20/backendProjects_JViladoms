/*!
	* Start Bootstrap - SB Admin v7.0.5 (https://startbootstrap.com/template/sb-admin)
	* Copyright 2013-2022 Start Bootstrap
	* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
	*/
// 
// Scripts
// 



// Toggle the side navigation
const sidebarToggle = document.body.querySelector('#sidebarToggle');
if (sidebarToggle) {
	// Uncomment Below to persist sidebar toggle between refreshes
	// if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
	//     document.body.classList.toggle('sb-sidenav-toggled');
	// }
	sidebarToggle.addEventListener('click', event => {
		event.preventDefault();
		document.body.classList.toggle('sb-sidenav-toggled');
		localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
	});
}
var formProductId = document.getElementById("formProductId");
var formProductTitle = document.getElementById("formTitle");
var formProductLocation = document.getElementById("formLocation");
var formProductDescription = document.getElementById("formDescription");
var formProductPrice = document.getElementById("formPrice");
var formProductImage = document.getElementById("formFile");
var btnFormCreate = document.getElementById("btnFormCreate");
var btnFormUpdate = document.getElementById("btnFormUpdate");
var btnFormCleanFields = document.getElementById("btnFormCleanFields");
var btnFormDelete = document.getElementById("btnFormDelete");

/*SHOW PRODUCTS*/

fetch("http://localhost:8080/apiFurnitureStore/getProducts")
	.then(response => response.json())
	.then(datos => {
		
		for (let producto of datos) {
			
			newRowProduct = createRow(producto.productId, producto.title, producto.location, producto.description, producto.price, producto.image);
			document.getElementById("bodyTableProduct").append(newRowProduct);
		}
	});

/*FIND PRODUCT BY ID*/

formProductId.addEventListener("blur", function () {
	if (formProductId.value != null && formProductId.value != "") {
		fetch("http://localhost:8080/apiFurnitureStore/getProduct?productId=" + formProductId.value)
			.then(response => response.json())
			.then(producto => {
				if (producto.title != null && producto.title != "") {
					formProductTitle.placeholder = producto.title;
				}
				if (producto.location != null && producto.location != "") {
					formProductLocation.placeholder = producto.location;
				}
				if (producto.description != null && producto.description != "") {
					formProductDescription.placeholder = producto.description
				}
				if (producto.price != null && producto.price != 0) {
					formProductPrice.placeholder = producto.price + "€";
				}
			})
	} else {
		formProductId.placeholder = "Introduce una id para buscar producto";
	}
})

/*CREATE PRODUCT*/

btnFormCreate.addEventListener("click", function () {
	fetch('http://localhost:8080/apiFurnitureStore/addProduct', {
		method: 'POST',
		body: JSON.stringify({
			location: formProductLocation.value,
			title: formProductTitle.value,
			description: formProductDescription.value,
			price: formProductPrice.value,
		}),
		headers: {
			'Content-type': 'application/json; charset=UTF-8',
		},
	})
		.then((response) => response.json())
		.then((json) => console.log(json));
})

/*UPDATE PRODUCT*/

btnFormUpdate.addEventListener("click", function () {
	fetch('http://localhost:8080/apiFurnitureStore/updateProduct/' + formProductId.value, {
		method: 'POST',
		body: JSON.stringify({
			productId: formProductId.value,
			location: formProductLocation.value,
			title: formProductTitle.value,
			description: formProductDescription.value,
			price: formProductPrice.value,
		}),
		headers: {
			'Content-type': 'application/json; charset=UTF-8',
		},
	})
		.then((response) => response.json())
		.then((json) => console.log(json));
})

/*DELETE PRODUCT*/

btnFormDelete.addEventListener("click", function () {

	fetch('http://localhost:8080/apiFurnitureStore/deleteProduct/' + formProductId.value, {
		method: 'DELETE',
	})
		.then((response) => response.json())
		.then((json) => console.log(json));
})

/*CLEAN FIELDS*/

btnFormCleanFields.addEventListener("click", function () {
	formProductTitle.placeholder = "";
	formProductLocation.placeholder = "";
	formProductDescription.placeholder = "";
	formProductPrice.placeholder = "";
})

function createRow(productId, title,  location, description, price, image) {
	let trProduct = document.createElement("tr");
	trProduct.classList.add("text-center");
	let tdProductId = document.createElement("td");
	let tdProductTitle = document.createElement("td");
	let tdProductDescription = document.createElement("td");
	let tdProductLocation = document.createElement("td");
	let tdProductPrice = document.createElement("td");
	let tdProductImage = document.createElement("td");
	let iImage = document.createElement("p");
	
	tdProductId.innerHTML = productId;
	tdProductTitle.innerHTML = title;
	tdProductLocation.innerHTML = location;
	tdProductDescription.innerHTML = description;
	tdProductPrice.innerHTML = price + "€";
	if(image != null)iImage.classList.add("bi","bi-check-lg", "text-success");else iImage.classList.add("bi", "bi-x-lg", "text-danger");
	
	trProduct.append(tdProductId);
	trProduct.append(tdProductTitle);
	trProduct.append(tdProductLocation);
	trProduct.append(tdProductDescription);
	trProduct.append(tdProductPrice);
	trProduct.append(tdProductImage);
	tdProductImage.append(iImage);

	return trProduct;
}

/*SELECT PRODUCT BY FILTER AND FILTER*/

let selectProductFilter = document.getElementById("selectProductFilter");
searchByFilter = document.getElementById("searchByFilter");
searchByFilter.addEventListener("keyup", function () {
  
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchByFilter");
  filter = input.value.toUpperCase();
  table = document.getElementById("tableAllProducts");
  tr = table.getElementsByTagName("tr");
  
  
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[selectProductFilter.value];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
});