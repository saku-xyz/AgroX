function Load(){
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
                let html = `<tr>
                              <td>`+element['adTitle']+`</td>
                              <td>`+element['adQuantity']+`</td>
                              <td>`+element['adPrice']+`</td>
                              <td>`+element['adType']+`</td>
                              <td class="d-flex">
                                   <a class="btn btn-primary btn-sm" style="margin:2px" data-toggle="modal"
                                         data-target="#exampleModal">Edit</a>
                                   <a class="btn btn-danger btn-sm" style="margin:2px" onclick="Delete('`+element['adId']+`')">Delete</a>
                              </td>
                            </tr>`



                items = items + html;
            });
            document.getElementById('TABLE').innerHTML= items;



        })
        .catch(function (error) {
            console.log(error);
        });
}


function AddNew(){
    let data = {
        "farmer": {
            "farmerId": 1
        },
        "adDate": "02.01.2020",
        "adTitle": document.getElementById('Title').value,
        "adQuantity": document.getElementById('Quantity').value,
        "adPrice": document.getElementById('Price').value,
        "adType": document.getElementById('Type').value,
        "adReviewed": true
    };
    var config = {
        method: 'post',
        url: 'http://127.0.0.1:8081/v1/advertisement',
        headers: {
            'Content-Type': 'application/json'
        },
        data : data
    };

    axios(config)
        .then(function (response) {
            document.getElementById('IMAGE').action = 'http://127.0.0.1:8081/v1/advertisement/'+response.data;
        })
}

function Delete(id){
    let items = {
        "farmer": {
            "farmerId": 1
        },
        "adDate": "02.01.2020",
        "adTitle": "fruit",
        "adQuantity": "20",
        "adPrice": 50.0,
        "adType": "fruit",
        "adReviewed": true
    };
    var config = {
        method: 'delete',
        url: 'http://127.0.0.1:8081/v1/advertisement/' + id,
        headers: {},
    };

    axios(config)
        .then(function (response) {
            window.location.reload();
        })
}