<template>
  <div class="flex flex-col">
    <label v-if="label">{{ label }}</label>
    <select
      v-model="internalValue"
      @change="emitSelection"
      :disabled="disabled"
    >
      <option
        v-for="option in modifiedOptions"
        :key="option.value"
        :value="option.value"
      >
        {{ option.text }}
      </option>
    </select>
  </div>
</template>

<script>
export default {
  name: "SelectComponent",
  props: {
    label: {
      type: String,
      default: "Selecione uma opção",
    },
    options: {
      type: Array,
      required: true,
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
    disabled: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      internalValue: this.modelValue,
      modifiedOptions: [...this.options],
    };
  },
  computed: {
    isValueMissing() {
      return !this.modifiedOptions.some(
        (option) => option.value === this.internalValue
      );
    },
  },
  watch: {
    modelValue(newVal) {
      this.internalValue = newVal;
      this.addMissingOption();
    },
    internalValue(newVal) {
      this.$emit("update:modelValue", newVal);
    },
  },
  methods: {
    emitSelection() {
      this.$emit("update:modelValue", this.internalValue);
    },
    addMissingOption() {
      if (this.isValueMissing && this.internalValue) {
        this.modifiedOptions.push({
          value: this.internalValue,
          text: `${this.internalValue} (Utilizado atualmente)`,
        });
      }
    },
  },
  created() {
    this.addMissingOption();
  },
};
</script>

<style scoped>
select {
  padding: 5px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

select:disabled {
  background-color: #e0e0e0;
  cursor: not-allowed;
}
</style>
