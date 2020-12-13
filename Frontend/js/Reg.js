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
        
            window.location.replace("./index.html");
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
        method: 'get',
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

                window.location.replace("./index.html");
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
    var config = {
        method: 'post',
        url: 'http://127.0.0.1:8081/v1/advertisement/rejected',
        headers: {},
    };

    axios(config)
        .then(function (response) {
            console.log(response.data);

            response.data.forEach(element => {
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
            });
            document.getElementById('Products').innerHTML= items;
            


        })
        .catch(function (error) {
            console.log(error);
        });


    


}