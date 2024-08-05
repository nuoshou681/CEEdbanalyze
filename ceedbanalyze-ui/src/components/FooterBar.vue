<template>
  <footer class="bottom">
    <hr>
    <div class="bottom-1">
      版权所有 Copyright 2024 Clan of 225 All Rights Reserved
    </div>
    <div class="bottom-2">
      <div>总访问量:2344</div>
      <div>当日访问量:223</div>
      <div>日均访问量:11</div>
      <div>登入时间:2023-12-14</div>
    </div>
  </footer>
</template>
<script>

</script>
<style>
.bottom {
  height: 50px;
  width: 1200px;
  background-color: antiquewhite;
  white-space: nowrap;

  & .bottom-1 {
    margin-left: 10px;
    height: 40px;
    float: left;
  }

  & .bottom-2 {
    width: 600px;
    height: 40px;
    float: right;
    display: flex;

    & div {
      text-align: center;
      height: 40px;
      width: 160px;
      padding-right: 10px;
    }
  }

}
</style>