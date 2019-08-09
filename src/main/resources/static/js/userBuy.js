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

    function getSchedule(ticket,ticketListContent) {
        console.log("getSchedule");
        getRequest(
            '/schedule/'+ticket.scheduleId,
            function (res) {
                if(res.success){
                    var schedule = res.content;
                    ticketListContent += '<div class="col-lg-10 table-wrapper"><div class="table"><div class="content"><div class="col-md-12 left">' +
                        '<div class="name"><b>' + schedule.movieName + '</b></div>' +
                        '<div class="hallNameAndSeat"><b>' +schedule.hallName +'  '
                        + (ticket.rowIndex+1)+ '排'+ (ticket.columnIndex+1) +'列</b></div><br>' +
                        '<div class="startTime"><b>开始时间：' +
                        schedule.startTime.toLocaleString() +
                        '</b></div>' +
                        '<div class="endTime"><b>结束时间：' +
                        schedule.endTime.toLocaleString() +
                        '</b></div>' +
                        '<div class="price"><b>票价：￥' +
                        schedule.fare +
                        '</b></div>' +
                        '<div class="payState"><b>' +
                        (ticket.state==1?'支付成功':'支付未成功') +
                        '</b></div>' +
                        '</div></div></div></div>';

                    $('#ticket-list').append(ticketListContent);

                    // console.log(ticketListContent);
                }else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function renderTicketList(list) {
        var ticketListContent='';
        for(let ticket of list){
            getSchedule(ticket,ticketListContent);
        }
    }

});