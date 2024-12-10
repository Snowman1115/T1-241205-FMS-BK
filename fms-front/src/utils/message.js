import {ElMessage} from 'element-plus';

const showMessage = (message, callback, type) => {
    ElMessage({
        type: type,
        message: message,
        duration: 2000,
        onClose: () => {
            if (callback) {
                callback();
            }
        }
    })
}

const message = {
    error: (message, callback) => {
        showMessage(message, callback, "error");
    },
    success: (message, callback) => {
        showMessage(message, callback, "success");
    },
    warning: (message, callback) => {
        showMessage(message, callback, "warning");
    }
}

export default message;

