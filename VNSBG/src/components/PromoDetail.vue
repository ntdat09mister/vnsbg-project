<template>
    <div class="promo-detail">
        <div class="icon-tick">
            <IconTick />
        </div>
        <span>{{ SpanPromoDetail }}</span>
        <br>
    </div>
</template>

<script lang="ts">import { defineComponent } from 'vue';
import IconTick from './icons/IconTick.vue';
export default defineComponent({
    components: {
        IconTick
    },
    props: {
        SpanPromoDetail: { type: String, require: true }
    }
})
</script>

<style>
.promo-detail {
    display: flex;
    flex-direction: row;
}
</style>