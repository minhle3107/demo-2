const BASE_URL = `http://localhost:8188`
const MAIN_CONTENT = document.getElementById('main-content')
const LOADING_SPINNER = document.getElementById('loading-spinner')


const getPostById = (id) => {
    LOADING_SPINNER.style.display = 'block';
    $.ajax({
        url: `${BASE_URL}/api/v1/posts/${id}`,
        type: 'GET',
        success: function (data) {
            console.log(data)
            MAIN_CONTENT.innerHTML = renderViewPostDetails(data.post, data.comments)
            LOADING_SPINNER.style.display = 'none';
        },
        error: function (error) {
            console.log(error)
            LOADING_SPINNER.style.display = 'none';
        }
    })
}


const currentUrl = window.location.href;

const url = new URL(currentUrl);

const id = url.pathname.split('/').pop();

console.log(id);

getPostById(id);

const renderViewPostDetails = (post, comments) => {

    if (!post) return '';

    return `
    <div class="row justify-content-center my-3">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex align-items-center mb-3">
                        <a href="#"><img src="${post.authorInfo.avatar}" alt="" width="50"
                                         class="rounded-circle"/></a>
                        <div class="flex-fill ps-2">
                            <div class="fw-bold"><a href="#" class="text-decoration-none"
                                                    >${post.authorInfo.authorName}</a></div>
                            <div class="small text-body text-opacity-50">${post.formattedCreatedAt}</div>
                        </div>
                    </div>

                    <p>${post.content}</p>
                    <div class="profile-img-list">
                        <div class="profile-img-list-item main">
                            <a href="#" data-lity="" class="profile-img-list-link">
                                <span class="profile-img-content"
                                      style="background-image: url(https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg);"></span>
                            </a>
                        </div>
                        <div class="profile-img-list-item main">
                            <a href="#" data-lity="" class="profile-img-list-link">
                                <span class="profile-img-content"
                                      style="background-image: url(https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_square.jpg);"></span>
                            </a>
                        </div>
                        <div class="profile-img-list-item">
                            <a href="#" data-lity="" class="profile-img-list-link">
                                <span class="profile-img-content"
                                      style="background-image: url(data:image/jpeg;base64,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);"></span>
                            </a>
                        </div>
                        <div class="profile-img-list-item">
                            <a href="#" data-lity="" class="profile-img-list-link">
                                <span class="profile-img-content"
                                      style="background-image: url(https://t4.ftcdn.net/jpg/00/97/58/97/360_F_97589769_t45CqXyzjz0KXwoBZT9PRaWGHRk5hQqQ.jpg);"></span>
                            </a>
                        </div>
                        <div class="profile-img-list-item">
                            <a href="#" data-lity="" class="profile-img-list-link">
                                <span class="profile-img-content"
                                      style="background-image: url(https://cdn.britannica.com/36/234736-050-4AC5B6D5/Scottish-fold-cat.jpg);"></span>
                            </a>
                        </div>
                        <div class="profile-img-list-item">
                            <a href="#" data-lity="" class="profile-img-list-link">
                                <span class="profile-img-content"
                                      style="background-image: url(https://t3.ftcdn.net/jpg/02/98/80/68/360_F_298806801_EuKxQEhFwckJKPnX9B8hnjL06cbj6LpA.jpg);"></span>
                            </a>
                        </div>
                    </div>
                    
                    <div class="d-flex justify-content-between my-2 gap-3">
                            ${renderTotalLikeAndComment(post.id, post.total_likes, post.total_comments)}
                        </div>
                        <hr class="mb-1 opacity-1"/>
                        
                        <div class="row text-center fw-bold">
                        
                            ${renderViewFeatLikeOrUnLike(post.liked_by_employee_1, post.id)}
                        
                            <div class="col">
                                <a href="${BASE_URL}/v1/posts/${post.id}" class="text-body text-opacity-50 text-decoration-none d-block p-2"> <i class="far fa-comment me-1 d-block d-sm-inline"></i> Comment </a>
                            </div>
                            <div class="col">
                                <a href="#" class="text-body text-opacity-50 text-decoration-none d-block p-2"> <i class="fa fa-share me-1 d-block d-sm-inline"></i> Share </a>
                            </div>
                        </div>
                    
                    <hr class="mb-1 opacity-1"/>

                    <div class="post-footer">
                        <form id="create-cmt-root" method="post" onsubmit="submitCommentRootForm(event, ${post.id}, ${post.commentId})" enctype="multipart/form-data">
                            <div class="input-group d-flex justify-content-between">
                                <input type="hidden" name="post_id" value="${post.id}">
                                <input class="form-control" placeholder="Add a comment" type="text" name="content" id="content-root">
                                <button type="button" class="btn btn-secondary" onclick="toggleIconList()">
                                    <i class="fas fa-smile"></i>
                                </button>
                                <label class="btn btn-primary">
                                    <i class="fas fa-upload"></i>
                                    <input type="file" id="comment-image" name="image" accept="image/*" style="display: none;" onchange="previewImage(event)">
                                </label>
                                <button class="btn btn-success" type="submit">
                                    <i class="fas fa-paper-plane"></i>
                                </button>
                                
                            </div>
                            <span class="text-danger" id="validate-content-root"></span>
                            <div id="image-preview" class="mt-2"></div>
                            <div id="icon-list" class="d-none mt-2">
                                <i class="fas fa-smile" onclick="insertIcon('😊')"></i>
                                <i class="fas fa-heart" onclick="insertIcon('❤️')"></i>
                                <i class="fas fa-thumbs-up" onclick="insertIcon('👍')"></i>
                                <i class="fas fa-laugh" onclick="insertIcon('😂')"></i>
                                <i class="fas fa-surprise" onclick="insertIcon('😮')"></i>
                                <i class="fas fa-sad-tear" onclick="insertIcon('😢')"></i>
                                <i class="fas fa-angry" onclick="insertIcon('😠')"></i>
                                <i class="fas fa-kiss-wink-heart" onclick="insertIcon('😘')"></i>
                                <i class="fas fa-grin-stars" onclick="insertIcon('🤩')"></i>
                                <i class="fas fa-meh" onclick="insertIcon('😐')"></i>
                            </div>
                        </form>
                        
                       
                        
                        <div class="comments-list">
                            ${renderViewCommentListRoot(comments)}
                            <!--                            End Comment Tree-->
                        </div>
                    </div>

                </div>
                <div class="d-flex justify-content-start mx-2">
                    <span class="read-more-comment" onclick="loadMoreItems()" id="read-more-comment">Read More</span>
                </div>
            </div>

        </div>
    </div>
    `
}


const renderTotalLikeAndComment = (postId, totalLike, totalComment) => {

    if (!totalLike && !totalComment) {
        return ''
    }

    if (!totalLike) {
        return `
        <span> </span>
        <span>
            <a href="${BASE_URL}/v1/posts/${postId}" class="text-decoration-none text-secondary">${totalComment} comments</a>
        </span>
        `
    }

    if (!totalComment) {
        return `
        <span>
            <a href="${BASE_URL}/v1/posts/${postId}" class="text-decoration-none text-secondary">${totalLike} likes</a>
        </span>
        <span> </span>
        `
    }

    return `
        <span>
            <a href="${BASE_URL}/v1/posts/${postId}" class="text-decoration-none text-secondary">${totalLike} likes</a>
        </span>
        <span>
            <a href="${BASE_URL}/v1/posts/${postId}" class="text-decoration-none text-secondary">${totalComment} comments</a>
        </span>
    `

}


const renderViewFeatLikeOrUnLike = (countLike, postId) => {

    if (countLike === 0) {
        return `
  
           <div class="col like-input">
                <form onclick="createLike(event, ${postId})" id="form-like-${postId}">
                       <input type="hidden" name="post_id" value="${postId}">
                                        
                       <a  class="text-body text-opacity-50 text-decoration-none d-block p-2"> <i class="far fa-thumbs-up me-1 d-block d-sm-inline"></i> Like </a>
                </form>
           </div>
  `
    }

    return `
                            <div class="col like-input">
                                <form  onclick="unLike(event, ${postId})" id="form-unlike-${postId}">
                            <input type="hidden" name="post_id" value="${postId}">
                                    <a  class="text-body text-opacity-50 text-decoration-none d-block p-2 text-primary"> 
                                        <i class="far fa-thumbs-up me-1 d-block d-sm-inline text-primary"></i> 
                                        <span class="text-primary">Like</span>
                                    </a>
                                </form>
                            </div>
    `

}


const createLike = (event, postId) => {
    event.preventDefault()
    console.log("Like")

    const formLike = document.getElementById(`form-like-${postId}`)

    const formData = new FormData(formLike)

    const likeData = {
        employee_id: 1,
        post_id: formData.get("post_id")
    }

    LOADING_SPINNER.style.display = 'block';

    $.ajax({
        url: `${BASE_URL}/api/v1/likes`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(likeData),
        success: function (data) {
            console.log("like success")
            getPostById(id);
            LOADING_SPINNER.style.display = 'none';

        },
        error: function (error) {
            console.log(error)
            LOADING_SPINNER.style.display = 'none';

        }
    })

}

const unLike = (event, postId) => {
    event.preventDefault()
    console.log("Like")

    const formLike = document.getElementById(`form-unlike-${postId}`)

    const formData = new FormData(formLike)

    const likeData = {
        employee_id: 1,
        post_id: formData.get("post_id")
    }

    LOADING_SPINNER.style.display = 'block';

    $.ajax({
        url: `${BASE_URL}/api/v1/unlikes`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(likeData),
        success: function (data) {
            console.log("unlike success")
            getPostById(id);
            LOADING_SPINNER.style.display = 'none';

        },
        error: function (error) {
            console.log(error)
            LOADING_SPINNER.style.display = 'none';

        }
    })
}

const renderViewCommentListRoot = (commentList) => {
    if (!commentList) return `<div></div>`;

    return commentList.map(comment => `
        <div class="card mb-3">
    <div class="card-body">
        <div class="d-flex">
            <a href="#" class="me-3">
                <img src="${comment.account.avatar}" alt="avatar" class="rounded-circle" width="50" height="50">
            </a>
            <div class="flex-grow-1">
                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">${comment.account.full_name}</h5>
                    <small class="text-muted">${comment.commentDetails.formattedCreatedAt}</small>
                </div>
                <p class="mb-1">${comment.commentDetails.content}</p>
                ${comment.commentDetails.imageUrl ? `<img src="${comment.commentDetails.imageUrl}" class="img-fluid rounded my-2" alt="thumbnail">` : ''}
                <div class="d-flex gap-2">
                    <span class="text-primary reply" role="button" onclick="showReplyForm(${comment.commentDetails.postId}, ${comment.commentDetails.id})">Reply</span>
                </div>
                ${renderViewCommentChild(comment.commentDetails.childComments, comment.commentDetails.postId)}
                <div id="reply-root-${comment.commentDetails.id}" class="mt-2"></div>
            </div>
        </div>
    </div>
</div>
         
    `).join('');
}

const renderViewCommentChild = (commentsChild, postId) => {
    if (!commentsChild) return `<div></div>`;

    return commentsChild.map(comment => `
        
        
        <div class="card mb-3 comment-child">
    <div class="card-body">
        <div class="d-flex">
            <a href="#" class="me-3">
                <img src="${comment.account.avatar}" alt="avatar" class="rounded-circle" width="50" height="50">
            </a>
            <div class="flex-grow-1">
                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">${comment.account.full_name}</h5>
                    <small class="text-muted">${comment.commentDetails.formattedCreatedAt}</small>
                </div>
                <p class="mb-1">${comment.commentDetails.content}</p>
                ${comment.commentDetails.imageUrl ? `<img src="${comment.commentDetails.imageUrl}" class="img-fluid rounded my-2" alt="thumbnail">` : ''}
                <div class="d-flex gap-2">
                    <span class="text-primary reply" role="button" onclick="showReplyForm(${comment.commentDetails.postId}, ${comment.commentDetails.parentId})">Reply</span>
                </div>
                ${renderViewCommentChild(comment.commentDetails.childComments, comment.commentDetails.postId)}
            </div>
        </div>
    </div>
</div>
        
    `).join('');
}

const showReplyForm = (postId, commentId) => {
    document.getElementById('reply-root-' + commentId).innerHTML = `
    <form id="replyForm-${postId}-${commentId}" onsubmit="submitReplyForm(event, ${postId}, ${commentId})"  enctype="multipart/form-data">
        <div class="input-group d-flex justify-content-between">
            <input type="hidden" name="post_id" value="${postId}">
            <input type="hidden" name="parent_id" value="${commentId}">
            <input class="form-control" placeholder="Add a comment" name="content" type="text" id="content-${commentId}">
            <button type="button" class="btn btn-secondary" onclick="toggleIconListReply(${commentId})">
                                    <i class="fas fa-smile"></i>
                                </button>
            <label class="btn btn-primary">
                                    <i class="fas fa-upload"></i>
                                    <input type="file" id="comment-image-${commentId}" name="image" accept="image/*" style="display: none;" onchange="previewImageChild(event, ${commentId})">
                                </label>
            <button class="btn btn-success" type="submit">
                                    <i class="fas fa-paper-plane"></i>
                                </button>
                                
                            </div>
                            <span class="text-danger" id="validate-content-${commentId}"></span>
                            
                            <div id="image-preview-${commentId}" class="mt-2"></div>
                            <div id="icon-list-reply-${commentId}" class="d-none mt-2">
                                <i class="fas fa-smile" onclick="insertIconReply('😊', ${commentId})"></i>
                                <i class="fas fa-heart" onclick="insertIconReply('❤️', ${commentId})"></i>
                                <i class="fas fa-thumbs-up" onclick="insertIconReply('👍', ${commentId})"></i>
                                <i class="fas fa-smile" onclick="insertIconReply('😊', ${commentId})"></i>
                                <i class="fas fa-heart" onclick="insertIconReply('❤️', ${commentId})"></i>
                                <i class="fas fa-thumbs-up" onclick="insertIconReply('👍', ${commentId})"></i>
                                <i class="fas fa-laugh" onclick="insertIconReply('😂', ${commentId})"></i>
                                <i class="fas fa-surprise" onclick="insertIconReply('😮', ${commentId})"></i>
                                <i class="fas fa-sad-tear" onclick="insertIconReply('😢', ${commentId})"></i>
                                <i class="fas fa-angry" onclick="insertIconReply('😠', ${commentId})"></i>
                                <i class="fas fa-kiss-wink-heart" onclick="insertIconReply('😘', ${commentId})"></i>
                                <i class="fas fa-grin-stars" onclick="insertIconReply('🤩', ${commentId})"></i>
                                <i class="fas fa-meh" onclick="insertIconReply('😐', ${commentId})"></i>
                            </div>
        </div>
    </form>
    
    `;
    document.getElementById(`content-${commentId}`).focus();
}

const submitCommentRootForm = (event) => {
    event.preventDefault();
    const form = document.getElementById('create-cmt-root');
    const formData = new FormData(form);
    const validateRoot = document.getElementById('validate-content-root');

    formData.append("employee_id", 1)

    if (!formData.get("content").trim()) {
        validateRoot.textContent = 'Content is required';
        validateRoot.focus();
        return false;
    }


    LOADING_SPINNER.style.display = 'block';
    $.ajax({
        url: `${BASE_URL}/api/v1/comments`,
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            getPostById(formData.get('post_id'));
            LOADING_SPINNER.style.display = 'none';
        },
        error: function (error) {
            console.error('Error:', error);
            LOADING_SPINNER.style.display = 'none';
        }
    });
}


const submitReplyForm = (event, postId, commentId) => {
    event.preventDefault();
    // const form = $(`#replyForm-${postId}-${commentId}`);
    const form = document.getElementById(`replyForm-${postId}-${commentId}`);

    const validateContentReply = document.getElementById(`validate-content-${commentId}`)

    const formData = new FormData(form);

    formData.append("employee_id", 1)

    if (!formData.get("content").trim()) {
        validateContentReply.textContent = "Content is required";
        validateContentReply.focus();
        return false;
    }


    LOADING_SPINNER.style.display = 'block';
    $.ajax({
        url: `http://localhost:8188/api/v1/comments`,
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            getPostById(postId)
            LOADING_SPINNER.style.display = 'none';

        },
        error: function (error) {
            console.error('Error:', error);
            LOADING_SPINNER.style.display = 'none';
        }
    });
}

function previewImage(event) {
    const imagePreview = document.getElementById('image-preview');
    imagePreview.innerHTML = '';
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const imgDiv = document.createElement('div');
            imgDiv.classList.add('image-container', 'position-relative', 'd-inline-block', 'me-2');
            imgDiv.innerHTML = `
                    <img src="${e.target.result}" class="img-thumbnail" width="100px" height="100px" alt="thumbnail">
                    <button type="button" class="btn btn-danger btn-sm position-absolute top-0 end-0" onclick="removeImage()"> 
                        <i class="fas fa-times"></i>
                    </button>
                `;
            imagePreview.appendChild(imgDiv);
        };
        reader.readAsDataURL(file);
    }
}

function removeImage() {
    document.getElementById('comment-image').value = '';
    document.getElementById('image-preview').innerHTML = '';
}

function previewImageChild(event, commentId) {
    const imagePreview = document.getElementById(`image-preview-${commentId}`);
    imagePreview.innerHTML = '';
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const imgDiv = document.createElement('div');
            imgDiv.classList.add('image-container', 'position-relative', 'd-inline-block', 'me-2');
            imgDiv.innerHTML = `
                    <img src="${e.target.result}" class="img-thumbnail" width="100px" height="100px" alt="thumbnail">
                    <button type="button" class="btn btn-danger btn-sm position-absolute top-0 end-0" onclick="removeImageChild(${commentId})"> 
                        <i class="fas fa-times"></i>
                    </button>
                `;
            imagePreview.appendChild(imgDiv);
        };
        reader.readAsDataURL(file);
    }
}

function removeImageChild(commentId) {
    document.getElementById(`comment-image-${commentId}`).value = '';
    document.getElementById(`image-preview-${commentId}`).innerHTML = '';
}

function toggleIconList() {
    const iconList = document.getElementById('icon-list');
    iconList.classList.toggle('d-none');
}

function insertIcon(icon) {
    const contentInput = document.getElementById('content-root');
    contentInput.value += icon;
    document.getElementById('icon-list');
}


function toggleIconListReply(commentId) {
    const iconList = document.getElementById(`icon-list-reply-${commentId}`);
    iconList.classList.toggle('d-none');
}

function insertIconReply(icon, commentId) {
    const contentInput = document.getElementById(`content-${commentId}`);
    contentInput.value += icon;
    document.getElementById(`icon-list-reply-${commentId}`);
}