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
		width: 700px;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">내용보기</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th width="20%" class="text-center">번호</th>
					<td width="30%" class="text-center">{{board_detail.no}}</td>
					<th width="20%" class="text-center">작성일</th>
					<td width="30%" class="text-center">{{board_detail.dbday}}</td>
				</tr>
				<%--
					 board_detail={"no":1, "name":"홍길동", hit:10}
				 --%>
				<tr>
					<th width="20%" class="text-center">이름</th>
					<td width="30%" class="text-center">{{board_detail.name}}</td>
					<th width="20%" class="text-center">조회수</th>
					<td width="30%" class="text-center">{{board_detail.hit}}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">제목</th>
					<td colspan="3">{{board_detail.subject}}</td>
				</tr>
				<tr>
					<%-- v-html --%>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space: pre-wrap">{{board_detail.content}}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<input type="button" value="수정" class="btn-success btn-xs" @click="update()">
						<input type="button" value="삭제" class="btn-info btn-xs" @click="del()" ref="delBtn"><!-- jquery는 ref대신 id -->
						<input type="button" value="목록" class="btn-warning btn-xs" @click="listData()">
						<!-- <a href="list.do" class="btn-warning btn-xs">목록</a> <!-- history.back()을 하면 조회수 증가가 안됨 -->
					</td>
				</tr>
				<!-- jquery -->
				<!-- <tr id="del" style="display: none">
					<td colspan="4" class="text-right">
						비밀번호: <input type="password" class="input-sm" ref="pwd">
						<input type="button" class="btn-sm btn-success" value="삭제">
					</td>
				</tr> -->
				<!-- Vue -->
				<tr v-show="isShow">
					<td colspan="4" class="text-right">
						비밀번호: <input type="password" class="input-sm" ref="pwd">
						<input type="button" class="btn-sm btn-success" value="삭제" @click="boardDelete()">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					board_detail:{},
					no:${no},
					change:0,
					isShow:false
				}
			},
			mounted(){ // $(function(){})영역
				/*
					$.ajax({
						type:'get',
						url:'',
						data:{},
						success:function(res)
						{
							
						}
					})
				*/
				axios.get('../board/detail_vue.do', {
					params:{
						no:this.no
					}
				}).then(response=>{
					console.log(response.data)
					this.board_detail=response.data
				}).catch(error=>{
					console.log(error.response)
				})
			},
			methods:{
				update(){
					location.href="../board/update.do?no=" + this.no
				},
				listData(){
					location.href="../board/list.do"
				},
				del(){
					if(this.change===0)
					{
						this.change=1
						//$('#delBtn').val("취소")      jquery
						//$('#del').show()             jquery
						this.isShow=true
						this.$refs.delBtn.value="취소"
					}
					else
					{
						this.change=0
						//$('#delBtn').val("삭제")      jquery
						//$('#del').hide()             jquery
						this.isShow=false
						this.$refs.delBtn.value="삭제"
					}
				},
				boardDelete(){
					let pwd=this.$refs.pwd.value
					if(pwd==="")
					{
						this.$refs.pwd.focus()
						return
					}
					
					axios.get('../board/delete_vue.do',{ // RestController의 value값이랑 같아야함
						params:{ // RestController의 매개변수값이랑 같아야함
							no:this.no,
							pwd:pwd
						}
					}).then(response=>{
						if(response.data==='yes')
						{
							location.href="../board/list.do"
						}
						else
						{
							alert("비밀번호가 틀립니다!!!")
							this.$refs.pwd.value=""
							this.$refs.pwd.focus()
						}
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount('.container')
	</script>
</body>
</html>