import { set } from "lodash";

export default {
  GENERIC_MUTATION(state, { property, value }) {
    set(state, property, value);
  },
};
