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
		width: 960px;
	}
	a:hover{
		cursor: pointer;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
				<div class="thumbnail">
			    	<a href="#">
			        	<img :src="'https://www.menupan.com'+vo.poster" style="width:100%">
			        	<div class="caption">
			          		<p>{{vo.name}}</p>
			        	</div>
			      	</a>
			    </div>
			</div>
		</div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a @click="prev()">&laquo;</a></li>
					<li v-for="i in range(startPage, endPage)" :class="i===curpage?'active':''"><a @click="pageChange(i)">{{i}}</a></li>
					<li v-if="endPage<totalpage"><a @click="next()">&raquo;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					food_list:[],
					curpage:1, 
					totalpage:0, 
					startPage:0, 
					endPage:0
				}
			}, 
			mounted(){
				this.dataSend()
			},
			methods:{
				dataSend(){
					axios.get("http://localhost:8080/web/food/list_vue.do", {
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.food_list=response.data // food_list 여기에 값이 다 들어 있음
						this.curpage=response.data[0].curpage
						this.totalpage=response.data[0].totalpage
						this.startPage=response.data[0].startPage
						this.endPage=response.data[0].endPage
					})
				},
				// 숫자 => 배열로 만들어 놔야 함
				range(start, end){
					let arr=[];
					let len=end-start
					for(let i=0;i<=len;i++)
					{
						arr[i]=start
						start++
					}
					return arr
				},
				// 페이지 관련
				pageChange(page){
					this.curpage=page
					this.dataSend()
				},
				prev(){
					this.curpage=this.startPage-1
					this.dataSend()
				},
				next(){
					this.curpage=this.endPage+1
					this.dataSend()
				}
			}
		}).mount('.container')
	</script>
</body>
</html>