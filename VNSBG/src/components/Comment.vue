<template>
    <div class="comment">
        <div class="pp1">
            <img src="https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png" style="margin-right: 9px">
        </div>
        <div class="input-write-comment">
            <input type="text" placeholder="Write your comment...">
        </div>
    </div>
</template>

<style>
.comment {
    display: flex;
    justify-content: space-between;
    width: 715px;
    height: 46px;
}

.comment .input-write-comment {
    width: 670px;
    height: 46px;
    border-radius: 15px;
}

.comment .input-write-comment input {
    background: none;
    outline: none;
    border-radius: 15px;
    width: 670px;
    height: 46px;
    font-size: 14px;
}

.comment .pp1 {
    width: 36px;
    height: 47.25px;
}

.comment .pp1 img {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    margin-top: 13px;
}
</style>