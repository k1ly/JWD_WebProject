let backToTop = $('#back-to-top');

$(document).ready(function () {
    if ($('#review-list')[0]) {
        loadReviewList(1);
    }
    if ($('#menu-content')[0]) {
        let pagination = $('#menu-content .pagination');
        createPagination(pagination, pagination.data('page-count'), pagination.data('indent'));
    }
    if ($('#ao-content')[0]) {
        let pagination = $('#ao-content .pagination');
        createPagination(pagination, pagination.data('page-count'), pagination.data('indent'));
    }
    if ($('#mo-content')[0]) {
        let pagination = $('#mo-content .pagination');
        createPagination(pagination, pagination.data('page-count'), pagination.data('indent'));
    }
    if ($('#ad-content')[0]) {
        let pagination = $('#ad-content .pagination');
        createPagination(pagination, pagination.data('page-count'), pagination.data('indent'));
    }
    if ($('#au-content')[0]) {
        let pagination = $('#au-content .pagination');
        createPagination(pagination, pagination.data('page-count'), pagination.data('indent'));
    }
    let cartEmpty = $('#cart-empty');
    if (cartEmpty[0] && $('#cart').length === 0)
        cartEmpty.show();
    let editImagePreview = $('.edit-dish-content .image-preview');
    let addImagePreview = $('.add-dish-content .image-preview');
    if (editImagePreview[0] && addImagePreview[0]) {
        ;['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
            editImagePreview[0].addEventListener(eventName, preventDefaults, false);
            addImagePreview[0].addEventListener(eventName, preventDefaults, false);
        })
        ;['dragenter', 'dragover'].forEach(eventName => {
            editImagePreview[0].addEventListener(eventName, highlightEdit, false);
            addImagePreview[0].addEventListener(eventName, highlightAdd, false);
        })
        ;['dragleave', 'drop'].forEach(eventName => {
            editImagePreview[0].addEventListener(eventName, unhighlightAdd, false);
            addImagePreview[0].addEventListener(eventName, unhighlightEdit, false);
        })
        editImagePreview[0].addEventListener('drop', handleDropEdit, false)
        addImagePreview[0].addEventListener('drop', handleDropAdd, false)
    }
})

$(window).scroll(function () {
    let header = $('.head');
    let docViewTop = $(window).scrollTop();
    let docViewBottom = docViewTop + $(window).height();
    let offsetTop = $('.footer').offset().top;
    if (docViewTop < 200) {
        header.css('background-color', 'rgba(32, 30, 40, 1)');
        backToTop.removeClass('scrolled');
    } else {
        header.css('background-color', 'rgba(32, 30, 40, 0.5)');
        backToTop.addClass('scrolled');
    }
    if (docViewTop <= offsetTop && offsetTop <= docViewBottom) {
        backToTop.addClass('hug-footer');
    } else {
        backToTop.removeClass('hug-footer');
    }
})

$(window).click(function (event) {
    if (event.target === registerWindow[0]) {
        registerWindow.hide();
    } else if (event.target === checkoutWindow[0]) {
        checkoutWindow.hide();
    } else {
        if (event.target !== $('#user-drop-btn')[0] && $(event.target).parents('#user-drop-btn').length === 0)
            $('#user-actions-dropdown').hide();
    }
})

backToTop.click(function () {
    window.scroll({
        top: 0,
        behavior: 'smooth',
    })
});

function createPagination(pagination, pageCount, indent) {
    if (pagination.data('page') > 1) {
        let prev = document.createElement('button');
        $(prev).addClass('page-button');
        $(prev).val(pagination.data('page') - 1).text("«");
        let li = document.createElement('li');
        $(li).append(prev);
        pagination.append(li);
    }
    for (let i = -indent; i <= indent; i++) {
        let value = pagination.data('page') + i;
        if (value > 0 && value <= pageCount) {
            let button = document.createElement('button');
            $(button).addClass('page-button');
            if (i === 0)
                $(button).addClass('active');
            $(button).val(value).text(value);
            let li = document.createElement('li');
            $(li).append(button);
            pagination.append(li);
        }
    }
    if (pagination.data('page') < pageCount) {
        let next = document.createElement('button');
        $(next).addClass('page-button');
        $(next).val(pagination.data('page') + 1).text("»");
        let li = document.createElement('li');
        $(li).append(next);
        pagination.append(li);
    }
}

// --------------------Auth--------------------

let passwordInput = $('#password');
let repeatPswInput = $('#repeat-psw');
let showPassword = $('#show-password');

passwordInput.focus(function () {
    $('#password-check').show();
})

passwordInput.blur(function () {
    $('#password-check').hide();
})

passwordInput.keyup(function () {
    let letter = $('#letter');
    let capital = $('#capital');
    let number = $('#number');
    let length = $('#length');

    if (passwordInput.val().match(/[a-z]/g)) {
        letter.removeClass('invalid');
        letter.addClass('valid');
    } else {
        letter.removeClass('valid');
        letter.addClass('invalid');
    }

    if (passwordInput.val().match(/[A-Z]/g)) {
        capital.removeClass('invalid');
        capital.addClass('valid');
    } else {
        capital.removeClass('valid');
        capital.addClass('invalid');
    }

    if (passwordInput.val().match(/[0-9]/g)) {
        number.removeClass('invalid');
        number.addClass('valid');
    } else {
        number.removeClass('valid');
        number.addClass('invalid');
    }

    if (passwordInput.val().length >= 8) {
        length.removeClass('invalid');
        length.addClass('valid');
    } else {
        length.removeClass('valid');
        length.addClass('invalid');
    }
})

showPassword.mousedown(function () {
    $('#password').prop('type', 'text');
    $('#repeat-psw').prop('type', 'text');
})

showPassword.mouseup(function () {
    $('#password').prop('type', 'password');
    $('#repeat-psw').prop('type', 'password');
})

showPassword.mouseleave(function () {
    $('#password').prop('type', 'password');
    $('#repeat-psw').prop('type', 'password');
})

repeatPswInput.keyup(function () {
    let repeatCheck = $('#repeat-check');
    repeatCheck.show();
    if ($(this).val() === passwordInput.val()) {
        repeatCheck.css('color', 'green');
        repeatCheck.text('✔');
    } else {
        repeatCheck.css('color', 'red');
        repeatCheck.text('❌');
    }
})

$('#register-form').submit(function (event) {
    event.preventDefault();
    if (repeatPswInput.val() !== passwordInput.val()) {
        alert('Повторите ваш пароль еще раз');
        return false;
    } else this.submit();
})

// --------------------Header--------------------

$('#user-drop-btn').click(function () {
    $('#user-actions-dropdown').toggle();
})

let registerWindow = $('#register-window');
let checkoutWindow = $('#checkout-window');

$('.cancel-order').click(function () {
    let order = $(this).parents('.awaiting.order');
    $.post('/restaurant/index', {
        command: 'CANCEL_ORDER',
        orderId: order.data('id')
    }, function (returnedData) {
        let obj = JSON.parse(returnedData);
        let order = $('.awaiting.order[data-id=' + obj.orderId + ']');
        if (obj.isCanceled)
            order.remove();
        if ($('.order').length === 0)
            $('#order-history-empty').show();
    })
})

// --------------------Settings--------------------

function changeSetting(settingAttribute) {
    settingAttribute.prop('disabled', false);
    $('#settings-inputs').hide();
}

$('#change-password').click(function () {
    changeSetting($('#settings-password'));
})

$('#change-name').click(function () {
    changeSetting($('#settings-name'));
})

$('#change-email').click(function () {
    changeSetting($('#settings-email'));
})

$('#change-phone').click(function () {
    changeSetting($('#settings-phone'));
})

$('#settings-form').submit(function () {
    if ($('input[id^=settings]:not(:disabled)').length === 0) {
        $('#settings-inputs').show();
        return false;
    }
})

// --------------------Manage--------------------

function changeOrderStatus(orderId, status) {
    $.post('/restaurant/index', {
        command: 'MANAGE_ORDER',
        orderId: orderId,
        status: status
    }, function () {
        document.location.reload();
    })
}

$('.accept-order').click(function () {
    let order = $(this).parents('.order');
    changeOrderStatus(order.data('id'), 'preparing');
})

$('.ready-order').click(function () {
    let order = $(this).parents('.order');
    changeOrderStatus(order.data('id'), 'ready');
})

$('.finish-order').click(function () {
    let order = $(this).parents('.order');
    changeOrderStatus(order.data('id'), 'finished');
})

$('.deny-order').click(function () {
    let order = $(this).parents('.order');
    changeOrderStatus(order.data('id'), 'not_paid');
})

// --------------------Welcome--------------------

let reviewGrade = $('#add-review-grade .star');
let reviewComment = $('#add-review-comment');
let commentContent = "";
let sortReviews = $('#sort-reviews');
let reviewList = $('#review-list');

$('#add-review-btn').click(function () {
    $('#add-review').show();
    $(this).hide();
})

reviewGrade.mouseenter(function () {
    for (let i = 0; i < reviewGrade.length; i++) {
        $(reviewGrade[i]).css('color', (i <= $(this).index()) ? 'orange' : '#FFE6ADFF');
    }
})

reviewGrade.mouseleave(function () {
    for (let i = 0; i < reviewGrade.length; i++) {
        $(reviewGrade[i]).css('color', "");
    }
})

reviewGrade.click(function () {
    let value = $(this).index() + 1;
    $('#grade').val(value);
    for (let i = 0; i < 5; i++) {
        if (i < value)
            $(reviewGrade[i]).addClass('active');
        else
            $(reviewGrade[i]).removeClass('active');
    }
})

reviewComment.focus(function () {
    $(this).trigger('change');
    commentContent = $(this).html();
}).on('blur keyup paste cut delete', function () {
    if (commentContent !== $(this).html()) {
        $(this).trigger('change');
    }
});

reviewComment.on('change', function () {
    if ($(this).html() === "")
        $(this).html('<br/>');
    let html = $(this).html().replace(/</g, "&lt;").replace(/>/g, "&gt;");
    $('#comment').val(html);
});

$('#review-cancel').click(function () {
    $('#add-review').hide();
})

$('#review-submit').click(function () {
    if (reviewComment.text() === "" && $('#add-review-grade').val() > 0) {
        $.post('/restaurant/index', {
            command: 'ADD_REVIEW',
            comment: $('#add-review input[name=comment]').val(),
            grade: $('#add-review input[name=grade]').val()
        }, function (returnedData) {
            let obj = JSON.parse(returnedData);
            if (obj.isAdded) {
                loadReviewList();
            }
        })
        let grade = $('#grade');
        grade.val(0);
        for (let i = 0; i < reviewGrade.length; i++) {
            if (i < grade.val())
                $(reviewGrade[i]).addClass('active');
            else
                $(reviewGrade[i]).removeClass('active');
        }
        reviewComment.html("");
        reviewComment.trigger('change');
    } else
        $('#add-review').hide();
})

sortReviews.change(function () {
    loadReviewList(1);
})

function loadReviewList(page) {
    reviewList.find('.pagination').empty();
    reviewList.find('table').empty();
    $.get('/restaurant/index', {
            command: 'GET_REVIEWS',
            asc: sortReviews.val(),
            count: reviewList.data('count'),
            page: page
        }, function (returnedData) {
            let obj = JSON.parse(returnedData);
            let pagination = reviewList.find('.pagination');
            pagination.data('page', obj.page);
            createPagination(pagination, obj.pageCount, 2);
            for (let i = 0; i < obj.reviewList.length; i++) {
                let newReview = document.createElement('div');
                let reviewHead = document.createElement('div');
                $(reviewHead).addClass('review-head');
                let userName = document.createElement('span');
                $(userName).text(obj.reviewList[i].user.name);
                $(reviewHead).append(userName);
                let date = document.createElement('span');
                $(date).text(obj.reviewList[i].date);
                $(reviewHead).append(date);
                let grade = document.createElement('span');
                $(grade).addClass('review-grade')
                let stars = [];
                for (let j = 0; j < 5; j++) {
                    stars[j] = document.createElement('span');
                    $(stars[j]).html('&starf;');
                    $(stars[j]).addClass('star');
                    if (j < obj.reviewList[i].grade)
                        $(stars[j]).addClass('active');
                    $(grade).append(stars[j]);
                }
                $(reviewHead).append(grade);
                $(newReview).append(reviewHead);
                let comment = document.createElement('div');
                $(comment).text(obj.reviewList[i].comment);
                $(newReview).append(comment);
                $(newReview).addClass('review');
                let td = document.createElement('td');
                $(td).append(newReview);
                let tr = document.createElement('tr');
                $(tr).append(td);
                reviewList.find('table').append(tr);
            }
            if (reviewList.find('table').find('tr').length === 0)
                $('#review-list-empty').show();
            else $('#review-list-empty').hide();
        }
    )
}

$(document).on('click', '#review-list .page-button', function (event) {
    loadReviewList($(event.target).val());
});

// --------------------Menu--------------------

$('#sort-menu').change(function () {
    this.form.submit();
})

$('#sort-menu-attr').change(function () {
    this.form.submit();
})

$('.menu-add-dish').click(function () {
    let cart = $('.cart');
    cart.css('background-color', '#b5eaff').animate({outlineWidth: '10px'}, 1000, 'easeOutQuint', function () {
        cart.css({
            'outline-width': '0',
            'background-color': ''
        });
    });
    let menuDish = $(this).parents('.menu-dish');
    $.post('/restaurant/index', {
        command: 'UPDATE_CART_ITEM',
        dishId: menuDish.data('id'),
        quantity: 1,
        isNew: true
    })
})

// --------------------Order--------------------

function updateCartItemQuantity(cartItem, quantity) {
    $.post('/restaurant/index', {
        command: 'UPDATE_CART_ITEM',
        dishId: cartItem.data('id'),
        quantity: quantity,
        isNew: false
    }, function (returnedData) {
        let obj = JSON.parse(returnedData);
        if (obj.isUpdated) {
            let cartItem = $('.cart-item[data-id=' + obj.orderItem.dish.id + ']');
            if (obj.orderItem.quantity === 0) {
                cartItem.remove();
                if ($('#cart .cart-item').length === 0) {
                    $('#cart').remove();
                    $('#checkout').remove();
                    $('#cart-empty').show();
                }
            } else {
                cartItem.data('quantity', obj.orderItem.quantity);
                cartItem.find('.cart-item-quantity').val(obj.orderItem.quantity);
            }
        }
    })
}

$('.cart-delete').click(function () {
    let cartItem = $(this).parents('.cart-item');
    updateCartItemQuantity(cartItem, 0);
})

$('.cart-minus').click(function () {
    let cartItem = $(this).parents('.cart-item');
    updateCartItemQuantity(cartItem, cartItem.data('quantity') - 1);
})

$('.cart-item-quantity').focusout(function () {
        let cartItem = $(this).parents('.cart-item');
        let quantity = parseInt($(this).val(), 10);
        if (!isNaN(quantity)) {
            quantity = Math.max(0, quantity);
            updateCartItemQuantity(cartItem, quantity);
        } else {
            $(this).val(cartItem.data('quantity'));
        }
    }
)

$('.cart-plus').click(function () {
    let cartItem = $(this).parents('.cart-item');
    updateCartItemQuantity(cartItem, cartItem.data('quantity') + 1);
})

$('#checkout').click(function () {
    let isUserRegistered = $('#register-window').length === 0;
    if (isUserRegistered) {
        let totalPrice = 0;
        let cartItems = $('#cart .cart-item');
        for (let i = 0; i < cartItems.length; i++) {
            let price = $(cartItems[i]).data('price');
            let discount = $(cartItems[i]).data('discount');
            let quantity = $(cartItems[i]).data('quantity');
            totalPrice += (discount != null ? price / 100 * (100 - discount) : price) * quantity;
        }
        let checkoutTotalPrice = $('#total-price');
        checkoutTotalPrice.text("Итоговая сумма: " + totalPrice);
        checkoutWindow.show();
    } else {
        registerWindow.show();
    }
})

$('#close-register').click(function () {
    registerWindow.hide();
})

$('#close-checkout').click(function () {
    checkoutWindow.hide();
})

// --------------------Admin--------------------

let categoryEditor = $('#edit-category-window');
let categoryForm = $('#add-category-window');
let userEditor = $('#edit-user-window');
let dishEditor = $('#edit-dish-window');
let dishForm = $('#add-dish-window');
let editPhotoUpload = $('#edit-photo-upload');
let addPhotoUpload = $('#add-photo-upload');

$('.edit-category').click(function () {
    clearCategoryEditor()
    $.get('/restaurant/index', {
        command: 'FIND_CATEGORY',
        categoryId: $(this).parents('.category').data('id')
    }, function (returnedData) {
        let obj = JSON.parse(returnedData);
        showCategoryEditor(obj.category);
    })
})

$('#add-category').click(function () {
    categoryForm.find('#add-category-name').val('');
    categoryForm.show();
})

$('#close-add-category').click(function () {
    categoryForm.hide();
})

$('.delete-category').click(function () {
    $.post('/restaurant/index', {
        command: 'DELETE_CATEGORY',
        categoryId: $(this).parents('.category').data('id')
    }, function () {
        document.location.reload();
    })
})

function clearCategoryEditor() {
    categoryEditor.find('#edit-category-id').val('');
    categoryEditor.find('#edit-category-name').val('');
}

function showCategoryEditor(category) {
    categoryEditor.find('#edit-category-id').val(category.id);
    categoryEditor.find('#edit-category-name').val(category.name);
    categoryEditor.show();
}

$('#close-edit-category').click(function () {
    categoryEditor.hide();
})

$('#edit-category-submit').click(function () {
    let inputs = $(this.form).find('input');
    for (let i = 0; i < inputs.length; i++) {
        $(inputs[i]).prop('disabled', $(inputs[i]).data('disabled'));
    }
})

$('#sort-users, #sort-users-attr').change(function () {
    this.form.submit();
})

$('.edit-user').click(function () {
    clearUserEditor()
    $.get('/restaurant/index', {
        command: 'FIND_USER',
        userId: $(this).parents('.user').data('id')
    }, function (returnedData) {
        let obj = JSON.parse(returnedData);
        showUserEditor(obj.user, obj.roleList, obj.statusList);
    })
})

function clearUserEditor() {
    userEditor.find('#user-id').val('');
    userEditor.find('#user-role').empty();
    userEditor.find('#user-status').empty();
}

function showUserEditor(user, roleList, statusList) {
    userEditor.find('#user-id').val(user.id);
    for (let i = 0; i < roleList.length; i++) {
        let role = document.createElement('option');
        $(role).val(roleList[i].id).text(roleList[i].name);
        if (user.role.id === roleList[i].id)
            $(role).prop('selected', 'true');
        userEditor.find('#user-role').append(role);
    }
    for (let i = 0; i < statusList.length; i++) {
        let status = document.createElement('option');
        $(status).val(statusList[i].id).text(statusList[i].name);
        if (user.status.id === statusList[i].id)
            $(status).prop('selected', 'true');
        userEditor.find('#user-status').append(status);
    }
    userEditor.show();
}

$('#close-edit-user').click(function () {
    userEditor.hide();
})

$('#user-role, #user-status').click(function () {
    $(this).data('disabled', false);
})

$('#edit-user-submit').click(function () {
    let inputs = $(this.form).find('input');
    for (let i = 0; i < inputs.length; i++) {
        $(inputs[i]).prop('disabled', $(inputs[i]).data('disabled'));
    }
})

$('#sort-dishes, #sort-dishes-attr').change(function () {
    this.form.submit();
})

$('.edit-dish').click(function () {
    clearDishEditor();
    $.get('/restaurant/index', {
        command: 'FIND_DISH',
        dishId: $(this).parents('.dish').data('id')
    }, function (returnedData) {
        let obj = JSON.parse(returnedData);
        showDishEditor(obj.dish, obj.categoryList);
    })
})

function clearDishEditor() {
    editPhotoUpload[0].file = [];
    editPhotoUpload.trigger('change');
    dishEditor.find('#edit-dish-id').val('');
    dishEditor.find('#edit-dish-name').val('');
    dishEditor.find('#edit-dish-composition').val('');
    dishEditor.find('#edit-dish-weight').val('');
    dishEditor.find('#edit-dish-price').val('');
    dishEditor.find('#edit-dish-discount').val('');
    dishEditor.find('#edit-dish-category').empty();
}

function showDishEditor(dish, categoryList) {
    dishEditor.find('#edit-dish-id').val(dish.id);
    dishEditor.find('#edit-dish-name').val(dish.name);
    dishEditor.find('#edit-dish-composition').val(dish.composition);
    dishEditor.find('#edit-dish-weight').val(dish.weight);
    dishEditor.find('#edit-dish-price').val(dish.price);
    dishEditor.find('#edit-dish-discount').val(dish.discount);
    for (let i = 0; i < categoryList.length; i++) {
        let category = document.createElement('option');
        $(category).val(categoryList[i].id).text(categoryList[i].name);
        if (dish.category.id === categoryList[i].id)
            $(category).prop('selected', 'true');
        dishEditor.find('#edit-dish-category').append(category);
    }
    dishEditor.show();
}

$('#close-edit-dish').click(function () {
    dishEditor.hide();
})

editPhotoUpload.click(function () {
    $(this).data('disabled', false);
})

$('#edit-dish-name, #edit-dish-composition, #edit-dish-weight, #edit-dish-price, #edit-dish-discount, #edit-dish-category').click(function () {
    $(this).data('disabled', false);
})

$('#edit-dish-submit').click(function () {
    let inputs = $(this.form).find('input');
    for (let i = 0; i < inputs.length; i++) {
        $(inputs[i]).prop('disabled', $(inputs[i]).data('disabled'));
    }
})

$('#add-dish').click(function () {
    clearDishForm();
    $.get('/restaurant/index', {
        command: 'FIND_DISH',
    }, function (returnedData) {
        let obj = JSON.parse(returnedData);
        showDishForm(obj.categoryList);
    })
})

function clearDishForm() {
    addPhotoUpload[0].file = [];
    addPhotoUpload.trigger('change');
    dishForm.find('#add-dish-name').val('');
    dishForm.find('#add-dish-composition').val('');
    dishForm.find('#add-dish-weight').val('0');
    dishForm.find('#add-dish-price').val('0');
    dishForm.find('#add-dish-discount').val('0');
    dishForm.find('#add-dish-category').empty();
}

function showDishForm(categoryList) {
    for (let i = 0; i < categoryList.length; i++) {
        let category = document.createElement('option');
        $(category).val(categoryList[i].id).text(categoryList[i].name);
        dishForm.find('#add-dish-category').append(category);
    }
    dishForm.show();
}

$('#close-add-dish').click(function () {
    dishForm.hide();
})


$('.delete-dish').click(function () {
    $.post('/restaurant/index', {
        command: 'DELETE_DISH',
        dishId: $(this).parents('.dish').data('id')
    }, function () {
        document.location.reload();
    })
})

function preventDefaults(e) {
    e.preventDefault();
    e.stopPropagation();
}

function highlightEdit() {
    $('#image-preview').addClass('highlight');
}

function highlightAdd() {
    $('#image-preview').addClass('highlight');
}

function unhighlightEdit() {
    $('#image-preview').removeClass('highlight');
}

function unhighlightAdd() {
    $('#image-preview').removeClass('highlight');
}

function handleDropEdit(e) {
    let dt = e.dataTransfer;
    editPhotoUpload[0].files = dt.files;
    editPhotoUpload.trigger('change');
}

function handleDropAdd(e) {
    let dt = e.dataTransfer;
    addPhotoUpload[0].files = dt.files;
    addPhotoUpload.trigger('change');
}

editPhotoUpload.change(function () {
    let text = $('.edit-dish-content .preview-text');
    text.hide();
    $('.edit-dish-content .preview-warning').hide();
    if (this.files.length > 0) {
        ([...this.files]).forEach(previewImageEdit);
    } else {
        $('.edit-dish-content .preview-img').hide();
        text.show();
    }
});

addPhotoUpload.change(function () {
    let text = $('.add-dish-content .preview-text');
    text.hide();
    $('.add-dish-content .preview-warning').hide();
    if (this.files.length > 0) {
        ([...this.files]).forEach(previewImageAdd);
    } else {
        $('.add-dish-content .preview-img').hide();
        text.show();
    }
});

function previewImageEdit(file) {
    if (isImageValid(file)) {
        let reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            let img = $('.edit-dish-content .preview-img');
            img.prop('src', reader.result.toString());
            img.show()
        }
    } else {
        $('.edit-dish-content .preview-img').hide();
        let warning = $('.edit-dish-content .preview-warning');
        warning.show();
        warning.text(file.name + ": Не подходящий тип файла");
    }
}

function previewImageAdd(file) {
    if (isImageValid(file)) {
        let reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            let img = $('.add-dish-content .preview-img');
            img.prop('src', reader.result.toString());
            img.show()
        }
    } else {
        $('.add-dish-content .preview-img').hide();
        let warning = $('.add-dish-content .preview-warning');
        warning.show();
        warning.text(file.name + ": Не подходящий тип файла");
    }
}

function isImageValid(file) {
    return ["image/jpeg", "image/png", "image/gif"].includes(file.type);
}

