<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container-fluid{
		margin-top: 50px;
	}
	.row{
		margin: 0px auto;
		width: 100%;
	}
	.images:hover{
		cursor: pointer;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<input type="text" class="input-sm" size="25" ref="fd" v-model="fd" @keyup.enter="find()">
						<input type="button" class="btn-sm btn-danger" value="검색" @click="find()">
					</td>
				</tr>
			</table>
		</div>
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
				<div class="thumbnail">
					<img :src="'https://www.menupan.com'+vo.poster" alt="Lights" style="width:100%" class="images" v-on:click="detail(vo.fno)">
					<div class="caption">
					    <p>{{vo.name}}</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					food_list:[], 
					fd:'마포'
				}
			},
			// find.do?address=마포
			mounted(){
				this.send()
			}, 
			methods: {
				send(){
					axios.get("http://localhost:8080/web/food/find.do", {
						params:{
							address:this.fd
						}
					}).then(response=>{
						console.log(response.data)
						this.food_list=response.data
					})
				}, 
				find(){
					let fds=this.$refs.fd.value;
					if(fds=="")
					{
						alert("검색어 입력!!!")	
						this.$refs.fd.focus() // VueJs에서는 id 대신 ref를 씀. ref의 모든 것을 가지고 오려면 @refs를 주면 됨. this.$refs.fd.focus()뜻은 모든 ref의 값 중 fd를 찾아서 포커스
						return
					}
					this.send()
				}
			}
		}).mount('.container')
	</script>
</body>
</html>