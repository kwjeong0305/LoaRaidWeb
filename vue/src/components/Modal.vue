<template>
  <div class="modal">
    <div class="overlay" @click="$emit('closeModal')"></div>
    <div class="modal-card">
      <slot/>
    </div>
  </div>
</template>

<script>
export default {
  name: "Modal"
}
</script>

<style>
/* Modal */
.modal, .overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1;
}

.overlay {
  opacity: 0.5;
  background-color: black;
}

.modal-card {
  position: relative;
  max-width: 20%;
  margin: 30px auto auto;
  padding: 20px;
  background-color: white;
  min-height: 520px;
  z-index: 99999;
  opacity: 1;
}
</style>
