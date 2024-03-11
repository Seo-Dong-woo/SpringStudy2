<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$('#msg').keyup(function(){
			$('#print').text($('#msg').val())
		})
	})
</script> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top: 50px;
	}
	.row{
		margin: 0px auto;
		width: 800px;
	}
</style>
</head>
<%--
	Vue: 1. ���� ���� ��� => �ӵ��� ������ ó��
		 mount => ���� �޸𸮿� ���� => �±� ������ ����
		 => String/StringBuffer
		 2. �����ֱ� => callback
		 3. ��Ƽ�� => ���
		 4. �������� => axios
		 5. ��� ���� => {{}} / :src=
		 6. ����� => v-model
		 7. router => ȭ�� ����
		 ===========================
		 8. vue-bootstrap
		 
		 1. �����ֱ� => vue3(react), vuex(mvc)
		    beforeCreate()
		    created()
		    --------------------- Vue ��ü ����
		    beforeMount
		    mounted()
		    --------------------- ���� �޸𸮿� HTML�� �ø� ���
		      => $(funtion(){}), window.onload
		      => �������� �����͸� �д´�: ��������� ����
		      => Jquery����
		    beforeUpdate()
		    updated()
		    --------------------- ������ ����
		      => Component: Main/Sub => sub�� ���� ���� => $emit
		    beforeDestroy()
		    destroyed()
		    --------------------- �޸� ����
 --%>
<body>
	<div class="container">
		<div class="row">
			<input type="text" size="30" class="input-sm" v-model="msg">
		</div>
		<div class="row">
			<div>{{msg}}</div>
		</div>
	</div>
	<script>
		// VueJs
		// Vue ��ü ����
		let app=Vue.createApp({
			data(){
				// ������� ����
				return {
					msg: ''
				}
			}, 
			// callback => Vue�� ���� �ڵ� ȣ��
			beforeCreate(){
				console.log("Vue ��ü ���� ��: beforeCreate() Call")
			}, 
			created(){
				console.log("Vue ��ü ���� �Ϸ�: created() Call")
			}, 
			beforeMount(){
				console.log("HTML�� �����͸� ���� �޸𸮿� �ö󰡱� ��: beforeMount() Call")
			}, 
			mounted(){
				console.log("���� �޸𸮿� HTML�� �ö� ����: window.onload, $(function), mounted Call")
			}, 
			beforeUpdate(){
				console.log("���� ��: beforeUpdate() Call")
			}, 
			updated(){
				console.log("���� �Ϸ�: update() Call")
			}, 
			beforeDestroy(){
				console.log("Vue ��ü �޸� ���� ��: beforeDestroy() Call")
			}, 
			destroyed(){
				console.log("Vue ��ü �޸� ���� �Ϸ�: destroyed() Call")
			}, 
			// ����� �޼ҵ�
			methods:{
				
			}
		}).mount('.container')
	</script>
</body>
</html>