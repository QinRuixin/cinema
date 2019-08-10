$(document).ready(function () {
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderTicketList(list) {

        for (var i = 0; i < list.length; i++) {
            var ticket = list[i];
            var schedule = ticket.schedule;
            var ticketListContent = '<div class="col-lg-5 table-wrapper"><div class="table"><div class="content"><div class="col-md-12 left">' +
                '<div class="name"><b>' + schedule.movieName + '</b></div>' +
                '<div class="hallNameAndSeat"><b>' +schedule.hallName +'  '
                + (ticket.rowIndex+1)+ '排'+ (ticket.columnIndex+1) +'列</b></div><br>' +
                '<div class="startTime"><b>开始时间：<br/>' +
                schedule.startTime.slice(0,10)+' '+schedule.startTime.slice(11,16)+
                '</br></div>' +
                '<div class="endTime"><b>结束时间：<br/>' +
                schedule.endTime.slice(0,10)+' '+schedule.endTime.slice(11,16)+
                '</b></div>' +
                '<div class="price"><b>票价：￥' +
                schedule.fare +
                '</b></div>' +
                (ticket.state=="支付完成"?'<div class="paySuccess">支付成功</div>':
                    '<div class="payFail">支付未成功</div>') +
                '</div></div></div></div>';

            $('#ticket-list').append(ticketListContent);

        }

    }

});