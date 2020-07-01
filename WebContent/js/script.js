$(document).ready(function () {
  // mockup cmt
  const comments = [
    {
      id: 1,
      username: "user 1",
      msg: "this is a text 1",
      replies: [
        {
          id: 11,
          username: "user rep 1",
          msg: "this is reply 1",
        },
      ],
    },
    {
      id: 2,
      username: "user 2",
      msg: "this is a text 2",
      replies: [
        // {
        //   id: 22,
        //   username: "user rep 2",
        //   msg: "this is reply 2",
        // },
      ],
    },
    {
      id: 3,
      username: "user 3",
      msg: "this is a text 3",
      replies: [
        {
          id: 33,
          username: "trung pro  123",
          msg: "thuan cute",
        },
      ],
    },
  ];

  $("body").on("click", "#reply", function () {
    // check reply box is active
    console.log($(this).parents(".reply").find(".add").length);
    if ($(this).parents(".reply").find(".add").length) {
      return;
    } else {
      const index = $(this).parents(".cmt").index();
      const currentRep = comments.find((val, idx) => idx === index);
      const cmt = $(".cmts .cmt").eq(index);

      $(` <div class="add">
    <a href="#"><img src="https://picsum.photos/id/1012/367/267" alt=""></a>
      <textarea name="" id="" rows="10" placeholder="Phản hồi"></textarea>
        </div>`).appendTo($(cmt).children(".reply"));
    }
  });

  //load comments
  // dung ajax goi api roi load data ra hieu k

  comments.forEach((comment) => {
    renderCommnent(comment);
  });

  let count = false;
  $("#list").click(function () {
    console.log("abc");
    count = !count;
    if (count) {
      $(".list").fadeIn();
    } else {
      $(".list").fadeOut();
    }
  });

  $("#landing").owlCarousel({
    loop: true,
    items: 4,
    autoplay: true,
    autoPlaySpeed: 5000,
    autoPlayTimeout: 5000,
    autoplayHoverPause: true,
    margin: 10,
  });

  $("#new-products").owlCarousel({
    loop: true,
    items: 4,
    autoplay: true,
    autoPlaySpeed: 5000,
    autoPlayTimeout: 5000,
    autoplayHoverPause: true,
  });
});

function renderCommnent(comment) {
  $(`
      <div class="cmt flex">
                  <a href="#"><img src="https://picsum.photos/id/1006/367/267" alt=""></a>

                  <div class="inf">
                     <p class="user">${comment.username}</p>
                     <p class="text">${comment.msg}</p>
                     <p class="rep" id="reply">Phản hồi</p>
                  </div>
                  <div class="reply">
                    <div class="reply-area">
                      ${renderReply(comment.replies)}
                    </div>
                  </div>
               </div>
 `).appendTo(".cmts");
}

function renderReply(list) {
  let content = "";
  list.forEach((item) => {
    content += `<div class="reply-item flex">
      <a href="#"><img src="https://picsum.photos/id/1012/367/267" alt=""></a>
        <div class="inf">
          <p class="user">${item.username}</p>
          <p class="text">${item.msg}</p>
         
        </div>
                      </div>`;
  });
  return content;
}

// function renderReplyBox(idx) {
//   <div class="add">
//     <a href="#"><img src="https://picsum.photos/id/1012/367/267" alt=""></a>
//       <textarea name="" id="" rows="10" placeholder="Phản hồi"></textarea>
//                     </div>
// }
