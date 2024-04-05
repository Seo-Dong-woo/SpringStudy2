<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
	a.alink:hover{
		cursor: pointer;
	}
</style>
</head>
<body>
	<div class="container" id="listApp">
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
				<div class="thumbnail">
					<a :href="'../food/detail.do?fno='+vo.fno">
				        <img :src="'https://www.menupan.com'+vo.poster" style="width:100%">
				        <div class="caption">
				          	<p style="font-size: 10px">{{vo.name}}</p>
				        </div>
			        </a>
				</div>
			</div>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a class="alink" @click="prev()">&laquo;</a></li>
					<li v-for="i in range(startPage, endPage)" :class="i==curpage?'active':''"><a class="alink" @click="pageChange(i)">{{i}}</a></li>
					<li v-if="endPage<totalpage"><a class="alink" @click="next()">&raquo;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
		let foodApp=Vue.createApp({
			data(){
				return{
					// 멤버변수
					food_list:[], // List => JSON
					curpage:1,
					totalpage:0,
					startPage:0,
					endPage:0
				}
			},
			mounted(){
				// CallBack => Vue에서 자동 호출되는 생명주기 함수 => onload() => $(function(){})
				this.dataRecv()
			},
			updated(){
				
			},
			// 사용자 정의 함수 => 이벤트, 데이터 읽기, 데이터 보내기
			methods:{
				dataRecv(){
					// 데이터 목록
					axios.get('../food/list_vue.do', {
						params:{
							// int page안에 채우는 값
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.food_list=response.data
					})
					
					// 페이지 정보 받기
					axios.get("../food/page_vue.do", {
						params:{
							page:this.curpage
						}
					}).then(response=>{
						/*
							map.put("curpage", page);
							map.put("totalpage", totalpage);
							map.put("startPage", startPage);
							map.put("endPage", endPage);
						*/
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
						this.startPage=response.data.startPage
						this.endPage=response.data.endPage
					})
				},
				range(start, end){
					// 페이지 v-for문
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
			}
		}).mount('#listApp')
	</script>
</body>
</html>