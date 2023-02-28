<template>
    <div>
        <input type="file" accept="image/*" class="hidden" ref="file" @change="change">
        <button @click="browse()">Browse</button>
    </div>
</template>

<script>
    props: {
        value: file
    },
    methods: {
        browse() {
            this.$ref.file.click();
        },
        change(e) {
            this.$emit('input', e.target.files[0]);
        }
    }
</script>