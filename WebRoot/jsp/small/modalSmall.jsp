<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title"></h4>
			<div class="alert alert-danger" style="display: none;margin-top: 20px;"></div>
		</div>
		<div class="modal-body">
			<div class="table-responsive">
				<table>
					<tr>
						<td style="width: 20%"><strong>收货地址：<br/><br/></strong></td>
						<td style="table-layout:fixed; word-break: break-all; overflow:hidden;"><span id="address"></span><br/><br/></td>
					</tr>
					<tr style="display: none;">
						<td style="width: 20%"><strong>卫裤型号：<br/><br/></strong></td>
						<td style="table-layout:fixed; word-break: break-all; overflow:hidden;"><span id="goodType"></span><br/><br/></td>
					</tr>
					<tr>
						<td style="width: 20%"><strong>物流公司：<br/><br/></strong></td>
						<td style="table-layout:fixed; word-break: break-all; overflow:hidden;"><span id="wuLiuName"></span><br/><br/></td>
					</tr>
					<tr>
						<td style="width: 20%"><strong>物流单号：<br/><br/></strong></td>
						<td style="table-layout:fixed; word-break: break-all; overflow:hidden;"><span id="wuLiuNumber"></span><br/><br/></td>
					</tr>
					<tr>
						<td style="width: 20%"><strong>物流价格：<br/><br/></strong></td>
						<td style="table-layout:fixed; word-break: break-all; overflow:hidden;"><span id="wuLiuPrice"></span><br/><br/></td>
					</tr>
					<tr>
						<td style="width: 20%"><strong>状态备注：<br/><br/></strong></td>
						<td style="table-layout:fixed; word-break: break-all; overflow:hidden;"><span id="statusNote"></span><br/><br/></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
