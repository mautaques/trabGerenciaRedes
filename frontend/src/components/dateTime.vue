<template>
  <div class="datetime-input">
    <label v-if="label">{{ label }}</label>
    <div class="input-group">
      <input
        type="date"
        v-model="date"
        :disabled="disabled"
        @input="updateDateTime"
        placeholder="YYYY-MM-DD"
      />
      <input
        type="time"
        v-model="time"
        :disabled="disabled"
        @input="updateDateTime"
        placeholder="HH:MM"
      />
      <button
        v-if="showLimpar"
        type="button"
        @click="clearFields"
        :disabled="disabled"
      >
        Limpar
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DateTimeInput",
  props: {
    showLimpar: Boolean,
    label: {
      type: String,
      default: "Data e Hora",
    },
    modelValue: {
      type: String,
      default: "",
    },
    disabled: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      date: "",
      time: "",
    };
  },
  watch: {
    modelValue: {
      immediate: true,
      handler(newValue) {
        if (newValue) {
          const [date, time] = newValue.split("T");
          this.date = date;
          this.time = time ? time.substring(0, 5) : ""; // Formato HH:MM
        } else {
          this.date = "";
          this.time = "";
        }
      },
    },
  },
  methods: {
    updateDateTime() {
      const currentDate = new Date().toISOString().split("T")[0];
      const selectedDate = this.date || currentDate;
      const selectedTime = this.time || "00:00";

      const formattedDateTime =
        this.date === "" && this.time === ""
          ? null
          : `${selectedDate}T${selectedTime}:00`;

      this.$emit("update:modelValue", formattedDateTime);
    },
    clearFields() {
      this.date = "";
      this.time = "";
      this.$emit("update:modelValue", null); // Envia `null` ao limpar os campos
    },
  },
};
</script>

<style scoped>
.datetime-input {
  display: flex;
  flex-direction: column;
}

.input-group {
  display: flex;
  align-items: center;
  gap: 8px;
}

input[type="date"],
input[type="time"],
button {
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #f0f0f0;
  cursor: pointer;
}

button:disabled,
input:disabled {
  background-color: #e0e0e0;
  cursor: not-allowed;
}
</style>
