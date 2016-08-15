function post (body) {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/controller', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send(body);
}

function goToUrl(url) {
	window.location.href = url;
}

function getData(item) {
	var length = item.length,
		data = '';
	for (var i = 0; i < length; i++) {
		if (item[i].type == 'checkbox' & item[i].checked) {
			data = data + item[i].value + ',';
		}
	}
	return data.slice(0, -1);
}

