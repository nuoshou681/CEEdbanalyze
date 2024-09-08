<template>
    <div class="chat-window">
        <div class="chat-window-header">
            <div class="chat-window-header-title">
                <span>高考小智聊天栏</span>
            </div>
            <div class="chat-window-header-close">
                <span @click="toggleChat">X</span>
            </div>
        </div>
        <div class="chat-window-body">
            <!-- 历史消息渲染 -->
            <div v-for="message in HistoryMessages" :key="message.id" :class="getMessageClass(message)">
                {{ message.message }}
            </div>
        </div>
        <div class="chat-window-footer">
            <input type="text" class="chat-window-footer-input" v-model="usermessage" placeholder="请输入您的问题...">
            <button class="chat-window-footer-send" @click="sendMessage">发送</button>
        </div>
    </div>
</template>

<script setup>
import { defineEmits, ref } from 'vue';
const usermessage = ref('');
// 历史消息列表
const HistoryMessages = ref([
    { id: 1, message: '你好！', type: 'bot' },
    { id: 2, message: '请问高考的注意事项有哪些？', type: 'user' },
    { id: 3, message: '高考时要注意保持良好的心态。', type: 'bot' }

]);
// 获取消息的样式
const getMessageClass = (message) => {
    return {
        'chat-window-body-message-bot': message.type === 'bot',
        'chat-window-body-message-user': message.type === 'user'
    };
};

// 关闭ChatWindow事件
const emit = defineEmits(['closeChatWindow']);
const toggleChat = () => {
    emit('closeChatWindow');
};
const sendMessage = () => {
    if (usermessage.value.trim() !== '') {
        HistoryMessages.value.push({
            id: Date.now(),
            message: usermessage.value,
            type: 'user'
        });
        usermessage.value = ''; // 清空输入框
    }
};
</script>

<style scoped>
.chat-window {
    position: fixed;
    bottom: 0;
    right: 0;
    width: 300px;
    height: 400px;
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.chat-window-enter-active,
.chat-window-leave-active {
    transition: transform 0.3s ease-in-out;
}

.chat-window-enter-from,
.chat-window-leave-to {
    transform: translateX(100%);
}

.chat-window-enter-to,
.chat-window-leave-from {
    transform: translateX(0);
}

.chat-window-header {
    background-color: #f0f0f0;
    padding: 10px;
    border-bottom: 1px solid #ccc;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    display: flex;
    justify-content: space-between;
}

.chat-window-header-title {
    font-weight: bold;
}

.chat-window-header-close {
    cursor: pointer;
}

.chat-window-body {
    padding: 10px;
    height: 300px;
    overflow-y: auto;
}

.chat-window-body-message-bot {
    background-color: #f0f0f0;
    padding: 10px;
    display: inline-block; /* 使背景宽度根据内容调整 */
    max-width: 80%; /* 限制最大宽度 */
    border-radius: 5px;
    margin-bottom: 10px;
}

.chat-window-body-message-user {
    background-color: #42b983;
    color: white;
    padding: 10px;
    display: inline-block; /* 使背景宽度根据内容调整 */
    max-width: 80%; /* 限制最大宽度 */
    border-radius: 5px;
    margin-bottom: 10px;
    margin-left: 15%;
}

.chat-window-footer {
    padding: 10px;
    display: flex;
}

.chat-window-footer-input {
    flex: 1;
    padding: 5px;
    width: 60%;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.chat-window-footer-send {
    padding: 5px 10px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
</style>
