function addFarmer() {
    var data = JSON.stringify({
        "farmerName": document.getElementById('name').value,
        "farmerContactNumber": document.getElementById('phone').value,
        "farmerLocation": document.getElementById('location').value,
        "farmerUsername": document.getElementById('uname').value,
        "farmerPassword": document.getElementById('password').value
    });
    
    var config = {
        method: 'post',
        url: 'http://127.0.0.1:8081/v1/farmer',
        headers: {
            'Content-Type': 'application/json'
        },
        data: data
    };



    axios(config)
        .then(function (response) {
        
            window.location.replace("./my-account.html");
        })
        .catch(function (error) {
            console.log(error);
        });
}

function loginFarmer() {
    let data = JSON.stringify({
        "farmerUsername": document.getElementById('uname').value,
        "farmerPassword": document.getElementById('password').value
    });
    
    var config = {
        method: 'post',
        url: 'http://127.0.0.1:8081/v1/farmer/login',
        headers: {
            'Content-Type': 'application/json'
        },
        data: data
    };



    axios(config)
        .then(function (response) {
            if (response.data === true){
                Cookies.set('Login','Logged')
                //Cookies.set('ID',);
                window.location.replace("./my-account.html");
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}


function getProducts(){
    

    let items = "";
    let items2 = "";
    var config = {
        method: 'post',
        url: 'http://127.0.0.1:8081/v1/advertisement/rejected',
        headers: {},
    };

    axios(config)
        .then(function (response) {
            console.log(response.data);

            response.data.forEach(element => {
                let html2 = `<div  class="list-view-box">
                                        <div class="row">
                                            <div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
                                                <div class="products-single fix">
                                                    <div class="box-img-hover">
                                                        <div class="type-lb">
                                                            <p class="new">New</p>
                                                        </div>
                                                        <img src="http://127.0.0.1:8081/v1/advertisement/img/`+element['adId']+`.png" class="img-fluid" alt="Image">
                                                        <div class="mask-icon">
                                                            <ul>
                                                                <li><a href="#" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>
                                                                <li><a href="#" data-toggle="tooltip" data-placement="right" title="Compare"><i class="fas fa-sync-alt"></i></a></li>
                                                                <li><a href="#" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
                                                            </ul>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6 col-lg-8 col-xl-8">
                                                <div class="why-text full-width">
                                                    <h4>`+element['adTitle']+`</h4>
                                                    <h5>LKR `+element['adPrice']+`</h5>
                                                    <h5>Quantity : `+element['adQuantity']+`</h5>
                                                    <p>Integer tincidunt aliquet nibh vitae dictum. In turpis sapien, imperdiet quis magna nec, iaculis ultrices ante. Integer vitae suscipit nisi. Morbi dignissim risus sit amet orci porta, eget aliquam purus
                                                        sollicitudin. Cras eu metus felis. Sed arcu arcu, sagittis in blandit eu, imperdiet sit amet eros. Donec accumsan nisi purus, quis euismod ex volutpat in. Vestibulum eleifend eros ac lobortis aliquet.
                                                        Suspendisse at ipsum vel lacus vehicula blandit et sollicitudin quam. Praesent vulputate semper libero pulvinar consequat. Etiam ut placerat lectus.</p>
                                                    <a class="btn hvr-hover" href="#">Add to Cart</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>`

                let html = `<div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
                    <div class="products-single fix">
                        <div class="box-img-hover">
                            <div class="type-lb">
                                <p class="sale">Sale</p>
                            </div>
                            <img src="http://127.0.0.1:8081/v1/advertisement/img/`+element['adId']+`.png" class="img-fluid" alt="Image">
                            <div class="mask-icon">
                                <ul>
                                    <li><a href="#" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>
                                    <li><a href="#" data-toggle="tooltip" data-placement="right" title="Compare"><i class="fas fa-sync-alt"></i></a></li>
                                    <li><a href="#" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
                                </ul>
                                <a class="cart" href="#">Add to Cart</a>
                            </div>
                        </div>
                        <div class="why-text">
                            <h4>`+element['adTitle']+`</h4>
                            <h5>`+element['adPrice']+`LKR</h5>
                        </div>
                    </div>
                </div>`



                items = items + html;
                items2 = items2 + html2;
            });
            document.getElementById('Products').innerHTML= items;
            document.getElementById('Products2').innerHTML= items2;
            


        })
        .catch(function (error) {
            console.log(error);
        });


    


}