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
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<goods_card v-bind:goods_all="goods_all"></goods_card>
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<div class="text-center">
				<pagination v-bind:page_list="page_list"></pagination>
			</div>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					goods_all:[],
					curpage:1,
					totalpage:0,
					startPage:0,
					endPage:0,
					page_list:{}
				}
			},
			mounted(){
				this.dataRecv()
			},
			components:{
				"goods_card":goodsCard,
				"pagination":pagination
			},
			methods:{
				dataRecv(){
					axios.get('../goods/all_vue.do', {
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.goods_all=response.data
					})
					
					axios.get('../goods/page_vue.do', {
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
		                this.page_list=response.data
		                this.curpage=response.data.curpage
		                this.totalpage=response.data.totalpage
		                this.startPage=response.data.startPage
		                this.endPage=response.data.endPage
					})
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
			}
		}).mount('.container')
	</script>
</body>
</html>