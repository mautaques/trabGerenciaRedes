import { createStore } from "vuex";
import storeModule from "@/views/store";

export default createStore({
  strict: process.env.NODE_ENV !== "production",
  modules: {
    storeModule,
  },
});
