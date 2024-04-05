const GoodsCard={
			props:['goods_all'],
			template:`<div class="col-md-3" v-for="vo in goods_all">
					 	<div class="thumbnail">
				    		<a href="#">
				        		<img :src="vo.goods_poster" style="width:100%">
				        		<div class="caption">
				          			<p style="font-size: 8px">{{vo.goods_name}}</p>
				        		</div>
				      		</a>
				    	</div>
					 </div>`
		}