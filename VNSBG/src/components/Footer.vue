<script lang="ts">
import { defineComponent } from 'vue';
import { userStore } from '@/stores/user';
import { mapState } from 'pinia';
export default defineComponent ({
    computed: {
        ...mapState(userStore, {
            loggedCustomer: 'loggedCustomer'
        }),
        wellcomeCustomer() {
            if (this.loggedCustomer != null) {
                return 'Xin chào ' + this.loggedCustomer + '! Chúc một ngày tốt lành 😘'
            } else {
                return 'Chúc một ngày tốt lành 😘'
            }
        }

    }
})
</script>

<template>
    <div class="footer">
        <footer>
            <h4>{{ wellcomeCustomer }}</h4>
        </footer>
    </div>
</template>