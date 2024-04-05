<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="wrapper row3" id="adminApp">
		<main class="container clear">
			<h2 class="sectiontitle">예약 현황</h2>
			<table class="table">
				<tr>
					<th>번호</th>
					<th></th>
					<th>아이디</th>
					<th>업체명</th>
					<th>예약일</th>
					<th>예약시간</th>
					<th>인원</th>
					<th></th>
				</tr>
				<tr v-for="vo in reserve_list">
					<td>{{vo.rno}}</td>
					<td>
						<img :src="'http://www.menupan.com'+vo.fvo.poster" style="width: 30px; height: 30px">
					</td>
					<td>{{vo.userId}}</td>
					<td>{{vo.fvo.name}}</td>
					<td>{{vo.rdate}}</td>
					<td>{{vo.rtime}}</td>
					<td>{{vo.rinwon}}</td>
					<td>
						<span class="btn btn-xs btn-danger" v-if="vo.reserve_ok==0" @click="ok(vo.rno)">승인대기</span>
						<span class="btn btn-xs btn-default" v-else>승인완료</span>
					</td>
				</tr>
			</table>
		</main>
	</div>
	<script>
		/*
			 data() => useState()
			 mounted() => useEffect()
			 methods => function
		*/
		let adminApp=Vue.createApp({
			data(){
				return{
					reserve_list:[]
				}
			},
			mounted(){ 
				// 전체 데이터를 가져오기 때문에 params:{}이 없음
				axios.get('../reserve/reserve_admin_vue.do')
				.then(response=>{
					console.log(response.data)
					this.reserve_list=response.data
				})
			},
			methods:{
				// 승인 버튼 => UPDATE, Mail
				ok(rno){
					axios.get('../reserve/reserve_ok_vue.do', {
						params:{
							rno:rno
						}
					}).then(response=>{
						if(response.data=='yes')
						{
							axios.get('../reserve/reserve_admin_vue.do')
							.then(response=>{
								console.log(response.data)
								this.reserve_list=response.data
							})
						}
						else
						{
							alert("예약 승인에 실패하셨습니다")
						}
					})
				}
			}
		}).mount('#adminApp')
	</script>
</body>
</html>