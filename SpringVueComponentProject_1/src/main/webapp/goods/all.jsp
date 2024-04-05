<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top: 50px;
	}
	.row{
		margin: 0px auto;
		width: 960px;
	}
	a.link:hover{
		cursor: pointer;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container" id="goodsAllApp">
		<div class="row">
			<div class="col-md-3" v-for="vo in goods_all">
			    <div class="thumbnail">
			    	<a href="#">
			        	<img :src="vo.goods_poster" alt="Lights" style="width:100%">
			        	<div class="caption">
			          		<p style="font-size: 8px">{{vo.goods_name}}</p>
			        	</div>
			      	</a>
			    </div>
			</div>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a class="link" @click="prev()">&laquo;</a><li>
					<li v-for="i in range(startPage, endPage)" :class="i===curpage?'active':''"><a class="link" @click="pageChange(i)">{{i}}</a><li>
					<li v-if="endPage<totalpage"><a class="link" @click="next()">&raquo;</a><li>
				</ul>
			</div>
		</div>
	</div>
	<script>
		let goodsAllApp=Vue.createApp({
			data(){
				return{
					goods_all:[],
					curpage:1,
					totalpage:0,
					startPage:0,
					endPage:0
				}
			},
			mounted(){
				this.dataRecv()
			},
			updated(){
				
			},
			methods:{
				dataRecv(){
					axios.get("../goods/all_vue.do", {
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.goods_all=response.data
					})
					
					axios.get("../goods/page_vue.do", {
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
						this.startPage=response.data.startPage
						this.endPage=response.data.endPage
					})
				},
				range(start, end){
					let arr=[]
					let leng=end-start
					for(let i=0;i<=leng;i++)
					{
						arr[i]=start
						start++
					}
					return arr
				},
				prev(){
					this.curpage=this.startPage-1
					this.dataRecv()
				},
				next(){
					this.curpage=this.endPage+1
					this.dataRecv()
				},
				pageChange(page){
					this.curpage=page
					this.dataRecv()
				}
			},
			components:{
				
			}
		}).mount('#goodsAllApp')
	</script>
</body>
</html>