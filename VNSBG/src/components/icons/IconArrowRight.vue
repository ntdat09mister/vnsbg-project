<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
    props: {
        stroke: { default: 'red' }
    },
    data() {
        return {
            
        }
    }
})
</script>

<template>
    <svg width="80px" height="80px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M20 12L4 12M20 12L14 18M20 12L14 6" :stroke="stroke" stroke-width="1.5" stroke-linecap="round"
            stroke-linejoin="round" />
    </svg>
</template>
    
