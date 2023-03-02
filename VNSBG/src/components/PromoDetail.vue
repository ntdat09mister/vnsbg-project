<template>
    <div class="promo-detail">
        <div class="icon-tick">
            <IconTick />
        </div>
        <span>{{ SpanPromoDetail }}</span>
        <br>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
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

.promo-detail span {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 600;
    font-size: 17px;
    line-height: 24px;
    /* identical to box height, or 171% */
    letter-spacing: 0.1px;
    color: #44444F;
}
</style>