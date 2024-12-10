<template>
  <form action="#" method="POST">
    <h2>Register</h2>

    <!-- Username Field -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <!-- Email Field -->
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <!-- Password Field -->
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <!-- Confirm Password Field -->
    <label for="confirm-password">Confirm Password:</label>
    <input type="password" id="confirm-password" name="confirm-password" required><br><br>

    <!-- Register Button -->
    <button type="submit">Register</button>

    <!-- Back Button -->
    <button type="button" onclick="window.history.back();">Back</button>
  </form>
</template>

<script setup>

</script>

<style lang="scss">

</style>