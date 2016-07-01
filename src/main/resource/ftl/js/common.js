function Map(){
this.container = new Object();
}

Map.prototype.put = function(key, value){
this.container[key] = value;
}

Map.prototype.get = function(key){
return this.container[key];
}

Map.prototype.keySet = function() {
var keyset = new Array();
var count = 0;
for (var key in this.container) {
// 跳过object的extend函数
if (key == 'extend') {
continue;
}
keyset[count] = key;
count++;
}
return keyset;
}

Map.prototype.size = function() {
var count = 0;
for (var key in this.container) {
// 跳过object的extend函数
if (key == 'extend'){
continue;
}
count++;
}
return count;
}

Map.prototype.remove = function(key) {
delete this.container[key];
}

function randomsort(a, b) {
	return Math.random()>0.5 ? -1 : 1;
}
function getCookie(name) {
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}