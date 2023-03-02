<script lang="ts">
import { defineComponent } from 'vue';

    export default defineComponent({
        props: {
            Contents: { type: String, require: true },
            ValueVModel: { type: String, require: true},
            ValueOptions1: { type: String, require: true},
            Option1: { type: String, require: true},
            Option2: { type: String, require: true},
            Option3: { type: String, require: true},
        }
    })
</script>

<template>
    <div class="option-search">
        <span>
            {{ Contents}}
        </span>
        <select name="" id="" :v-model= ValueVModel>
            <option :value=ValueOptions1>{{ Option1 }}</option>
        </select>
    </div>
</template>

<style>
.option-search {
    width: 122px;
    height: 60px;
}
</style>