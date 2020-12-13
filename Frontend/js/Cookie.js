function isLogged(){
    if (Cookies.get('Login') != null ){
        console.log('Logged IN');
    } else {
        document.getElementById('ACC').removeAttribute('hidden');
        console.log('Not Logged');
    }
}