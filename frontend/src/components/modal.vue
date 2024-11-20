<template>
  <div v-if="isVisible" class="modal-overlay" @click.self="close">
    <div class="modal-content">
      <div
        class="w-full p-2"
        style="border-bottom: 1px solid black; height: 50px"
      >
        <p style="font-size: 18px">{{ title }}</p>
        <button class="close-button" @click="close">X</button>
      </div>

      <div class="p-5">
        <slot></slot>
      </div>
      <div
        class="w-full p-2 flex justify-end"
        style="
          border-top: 1px solid black;
          min-height: 50px;
          height: fit-content;
        "
      >
        <div>
          <button
            class="p-2"
            style="border: 1px solid black; width: 75px"
            @click="$emit('salvar')"
          >
            Salvar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModalComponent",
  props: {
    title: String,
    isVisible: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    close() {
      this.$emit("close");
    },
  },
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}
.modal-content {
  background: white;
  border-radius: 8px;
  position: relative;
  width: 500px;
  min-width: 300px !important;
  max-width: fit-content;
  width: 100%;
}
.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 18px;
  cursor: pointer;
}
</style>
